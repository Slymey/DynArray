package DynArray;

public class DynArray<T>{
        T[][][][] t3=null;

        
        public DynArray(){ 
        }
        public void set(int ind, T el){
            if(t3==null){
                t3 = (T[][][][]) new Object[256][][][];
            }
            int a=ind>>(3*8)&0xff;
            T[][][] t2=t3[a];
            if(t2==null){
                t2 = (T[][][]) new Object[256][][];
                t3[a]=t2;
            }
            int b=ind>>(2*8)&0xff;
            T[][] t1=t2[b];
            if(t1==null){
                t1 = (T[][]) new Object[256][];
                t2[b]=t1;
            }
            int c=ind>>(1*8)&0xff;
            T[] t0=t1[c];
            if(t0==null){
                t0 = (T[]) new Object[256];
                t1[c]=t0;
            }
            int d=ind>>(0*8)&0xff;
            t0[d]=el;
        }
        public T get(int ind){
            if(t3==null){return null;}
            int a=ind>>(3*8)&0xff;
            T[][][] t2=t3[a];
            if(t2==null){return null;}
            int b=ind>>(2*8)&0xff;
            T[][] t1=t2[b];
            if(t1==null){return null;}
            int c=ind>>(1*8)&0xff;
            T[] t0=t1[c];
            if(t0==null){return null;}
            int d=ind>>(0*8)&0xff;
            return t0[d];
        }
    }
