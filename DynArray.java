import java.util.Arrays;

public class DynArray<T>{
    T[][][][] t3=null;


    public DynArray(){ 
    }
    public DynArray(T a[]){ 
        //In a language with better memory control could be done with next to no array copys
        int len = a.length;
        int t3s = (len>>>3*8)&0xff;//[t3s][][][]
        int t2s = (len>>>2*8)&0xff;//[][t2s][][]
        int t1s = (len>>>1*8)&0xff;//[][][t1s][]
        int t0s = (len     )&0xff;//[][][][256]
        int pos = 0;
        t3 = (T[][][][]) new Object[256][][][];
        for(int i=0; i<=t3s; i++){
            T[][][] t2 = (T[][][]) new Object[256][][];
            t3[i] = t2;
            int t2Max = (i==t3s)? t2s:255;
            for(int j=0; j<=t2Max; j++){
                T[][] t1 = (T[][]) new Object[256][];
                t2[j] = t1;
                int t1Max = (i==t3s&&j==t2s)? t1s:255;
                for(int k=0; k<=t1Max; k++){
                    T[] t0 = (T[]) new Object[256];
                    t1[k] = t0;
                    if(i==t3s&&j==t2s&&k==t1s){
                        System.arraycopy(a, pos, t0, 0, t0s);
                    }else{
                        System.arraycopy(a, pos, t0, 0, 256);
                        pos += 256;
                    }
                }
            }
        }
    }

    public int compact(){
        int ptr=0;
        if(t3==null)return 0;
        for(T[][][] t2:t3){
            if(t2==null)continue;
            for(T[][] t1:t2){
                if(t1==null)continue;
                for(T[] t0:t1){
                    if(t0==null)continue;
                    for(T t:t0){
                        if(t==null)continue;
                        this.set(ptr++, t);
                    }
                }
            }
        }
        return ptr;
    }
    public void setArray(int ind, T a[]){
        if(((long)ind+a.length)>=0x1l<<32)throw new ArrayIndexOutOfBoundsException();
        int len = a.length+ind;
        int p3s = (ind>>>3*8)&0xff;
        int p2s = (ind>>>2*8)&0xff;
        int p1s = (ind>>>1*8)&0xff;
        int p0s = (ind      )&0xff;
        int t3s = (len>>>3*8)&0xff;
        int t2s = (len>>>2*8)&0xff;
        int t1s = (len>>>1*8)&0xff;
        int t0s = (len      )&0xff;
        int pos = 0;
        if(t3==null)
            t3 = (T[][][][]) new Object[256][][][];
        for(int i=p3s; i<=t3s; i++){
            T[][][] t2 = t3[i];
            if(t2==null)
                t2 = (T[][][]) new Object[256][][];
            t3[i] = t2;
            int p2Min = (i==p3s)? p2s:0;
            int t2Max = (i==t3s)? t2s:255;
            for(int j=p2Min; j<=t2Max; j++){
                T[][] t1 = t2[j];
                if(t1==null)
                    t1 = (T[][]) new Object[256][];
                t2[j] = t1;
                int p1Min = (i==p3s&&j==p2s)? p1s:0;
                int t1Max = (i==t3s&&j==t2s)? t1s:255;
                for(int k=p1Min; k<=t1Max; k++){
                    T[] t0 = t1[k];
                    if(t0==null)
                        t0 = (T[]) new Object[256];
                    t1[k] = t0;
                    if(i==t3s&&j==t2s&&k==t1s){
                        System.arraycopy(a, pos, t0, 0, t0s);
                    }else if(i==p3s&&j==p2s&&k==p1s){
                        System.arraycopy(a, pos, t0, p0s, 256-p0s);
                        pos += p0s;
                    }else{
                        System.arraycopy(a, pos, t0, 0, 256);
                        pos += 256;
                    }
                }
            }
        }
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
    public T[] getArray(int ind, int len){
        T out[] = (T[]) new Object[len];
        len = len+ind;
        int p3s = (ind>>>3*8)&0xff;
        int p2s = (ind>>>2*8)&0xff;
        int p1s = (ind>>>1*8)&0xff;
        int p0s = (ind      )&0xff;
        int t3s = (len>>>3*8)&0xff;
        int t2s = (len>>>2*8)&0xff;
        int t1s = (len>>>1*8)&0xff;
        int t0s = (len      )&0xff;
        int pos = 0;
        if(t3==null)
            return out;
        for(int i=p3s; i<=t3s; i++){
            T[][][] t2 = t3[i];
            if(t2==null){
                pos+=0x1<<24;
                continue;
            }
            int p2Min = (i==p3s)? p2s:0;
            int t2Max = (i==t3s)? t2s:255;
            for(int j=p2Min; j<=t2Max; j++){
                T[][] t1 = t2[j];
                if(t1==null){
                    pos+=0x1<<16;
                    continue;
                }
                int p1Min = (i==p3s&&j==p2s)? p1s:0;
                int t1Max = (i==t3s&&j==t2s)? t1s:255;
                for(int k=p1Min; k<=t1Max; k++){
                    T[] t0 = t1[k];
                    if(t0==null){
                        pos+=0x1<<8;
                        continue;
                    }
                    if(i==t3s&&j==t2s&&k==t1s){
                        System.arraycopy(t0, 0, out, pos, t0s);
                    }else if(i==p3s&&j==p2s&&k==p1s){
                        System.arraycopy(t0, p0s, out, pos, 256-p0s);
                        pos += 256-p0s;
                    }else{
                        System.arraycopy(t0, 0, out, pos, 256);
                        pos += 256;
                    }
                }
            }
        }
        return out;
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
    @Override
    public String toString(){
        int len = this.compact();
        T arr[] = (T[]) this.getArray(0, len);
        return Arrays.toString(arr);
    }
}
