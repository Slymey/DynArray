# DynArray
A dynamic array that you can put/pull out of any integer index

## Definitions
### Java

Creates an empty DynArray that will store objects of type T
```java
public DynArray<T>()
```

Creates a DynArray of type T and fills it with an array
```java
public DynArray<T>(T a[])
```

Sets an object at the index
```java
set(int ind, T el)
```

Gets an object from the index
```java
T get(int ind)
```

Sets internal storage component at their coresponding indeces.<br>
All arrays should be precisely 256 elements, but sizes will not be checked.<br>
If larger arrays are set, out of bounds elements are inaccesible.<br>
If smaller arrays are set, you will get an index out of bounds axception when getting elements
```java
setRegion(int ind, T[][][] el)
setChunk(int ind, T[][] el)
setPage(int ind, T[] el)
```

Sets all the elements of the array starting at the index
```java
setArray(int ind, T a[])
```

Gets internal storage component from their coresponding indeces
```java
T[][][] getRegion(int ind)
T[][] getChunk(int ind)
T[] getPage(int ind)
```

Gets an array of elements of specified length starting at the index
```java
T[] getArray(int ind, int len)
```

Clones the DynArray into an new DynAray of same type, returning number of non null elements
```java
int clone(DynArray<T> d)
```

Clones the DynArray into an new DynAray of same type and compacts it by leaving out null elements, returns number of non null elements
```java
int cloneCompact(DynArray<T> d)
```

Compacts the DynArray by leaving out null elements, returns number of non null elements
```java
int compact()
```

Converts the entire DynArray into a compacted array of same type
```java
T[] toArray()
```

Stringifies the compacted DynArray
```java
String toString()
```


