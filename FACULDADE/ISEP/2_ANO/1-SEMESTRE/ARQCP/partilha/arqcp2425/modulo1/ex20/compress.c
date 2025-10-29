void compress(int* vec_ints, int n, long* vec_longs)
{
    for (int i = 0; i < n; i += 2) 
    {
        // Compress two consecutive integers into a long
        vec_longs[i / 2] = ((long)vec_ints[i] << 32) | (vec_ints[i + 1] & 0xFFFFFFFF);
    }
}
