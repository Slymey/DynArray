public class DynArray<T>{
        T[][][][] t3=null;

        
        public DynArray(){ 
        }
        public DynArray(T a[]){ 
            int len = a.length;
            int t3s = (len>>>3*8)&0xff;//[t3s][][][]
            int t2s = (len>>>2*8)&0xff;//[][t2s][][]
            int t1s = (len>>>1*8)&0xff;//[][][t1s][]
            int t0s = (len     )&0xff;//[][][][256]
            int pos = 0;
            t3 = (T[][][][]) new Object[256][][][];
            for (int i=0; i<=t3s; i++) {
                T[][][] t2 = (T[][][]) new Object[256][][];
                t3[i] = t2;
                int t2Max = (i==t3s)? t2s:255;
                for (int j=0; j<=t2Max; j++) {
                    T[][] t1 = (T[][]) new Object[256][];
                    t2[j] = t1;
                    int t1Max = (i==t3s&&j==t2s)? t1s:255;
                    for (int k=0; k<=t1Max; k++) {
                        T[] t0 = (T[]) new Object[256];
                        t1[k] = t0;
                        if (i==t3s&&j==t2s&&k==t1s) {
                            System.arraycopy(a, pos, t0, 0, t0s);
                        } else {
                            System.arraycopy(a, pos, t0, 0, 256);
                            pos += 256;
                        }
                    }
                }
            }
        }
        
        public void addArray(int ind, T a[]){
            if(((long)ind+a.length)>=0x1l<<32)throw new ArrayIndexOutOfBoundsException();
            /*
            incomplete
            */
            throw new UnsupportedOperationException("Not yet implemented!");
        }
        public void setRegion(int ind, T[][][] el){
            if(t3==null){
                t3 = (T[][][][]) new Object[256][][][];
            }
            int a=ind&0xff;
            t3[a]=el;
        }
        public void setChunk(int ind, T[][] el){
            if(t3==null){
                t3 = (T[][][][]) new Object[256][][][];
            }
            int a=ind>>>(1*8)&0xff;
            T[][][] t2=t3[a];
            if(t2==null){
                t2 = (T[][][]) new Object[256][][];
                t3[a]=t2;
            }
            int b=ind&0xff;
            t2[b]=el;
        }
        public void setPage(int ind, T[] el){
            if(t3==null){
                t3 = (T[][][][]) new Object[256][][][];
            }
            int a=ind>>>(2*8)&0xff;
            T[][][] t2=t3[a];
            if(t2==null){
                t2 = (T[][][]) new Object[256][][];
                t3[a]=t2;
            }
            int b=ind>>>(1*8)&0xff;
            T[][] t1=t2[b];
            if(t1==null){
                t1 = (T[][]) new Object[256][];
                t2[b]=t1;
            }
            int c=ind&0xff;
            t1[c]=el;
        }
        public void set(int ind, T el){
            if(t3==null){
                t3 = (T[][][][]) new Object[256][][][];
            }
            int a=ind>>>(3*8)&0xff;
            T[][][] t2=t3[a];
            if(t2==null){
                t2 = (T[][][]) new Object[256][][];
                t3[a]=t2;
            }
            int b=ind>>>(2*8)&0xff;
            T[][] t1=t2[b];
            if(t1==null){
                t1 = (T[][]) new Object[256][];
                t2[b]=t1;
            }
            int c=ind>>>(1*8)&0xff;
            T[] t0=t1[c];
            if(t0==null){
                t0 = (T[]) new Object[256];
                t1[c]=t0;
            }
            int d=ind>>>(0*8)&0xff;
            t0[d]=el;
        }
        public T[][][] getRegion(int ind){
            if(t3==null){return null;}
            ind = ind&0xff;
            return t3[ind];
        }
        public T[][] getChunk(int ind){
            if(t3==null){return null;}
            int a=ind>>>(1*8)&0xff;
            T[][][] t2=t3[a];
            if(t2==null){return null;}
            int b=ind&0xff;
            return t2[b];
        }
        public T[] getPage(int ind){
            if(t3==null){return null;}
            int a=ind>>>(2*8)&0xff;
            T[][][] t2=t3[a];
            if(t2==null){return null;}
            int b=ind>>>(1*8)&0xff;
            T[][] t1=t2[b];
            if(t1==null){return null;}
            int c=ind&0xff;
            return t1[c];
        }
        public T get(int ind){
            if(t3==null){return null;}
            int a=ind>>>(3*8)&0xff;
            T[][][] t2=t3[a];
            if(t2==null){return null;}
            int b=ind>>>(2*8)&0xff;
            T[][] t1=t2[b];
            if(t1==null){return null;}
            int c=ind>>>(1*8)&0xff;
            T[] t0=t1[c];
            if(t0==null){return null;}
            int d=ind>>>(0*8)&0xff;
            return t0[d];
        }
    }
