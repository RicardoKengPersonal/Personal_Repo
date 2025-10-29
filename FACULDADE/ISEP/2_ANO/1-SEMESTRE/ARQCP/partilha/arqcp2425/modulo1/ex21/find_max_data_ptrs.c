int *find_max_data_ptrs(int **ptrs, int n) 
{
    int *max_ptr = ptrs[0];  // Assume the first element is the largest initially

    for (int i = 1; i < n; i++) 
    {
        if (*ptrs[i] > *max_ptr)
        {
            max_ptr = ptrs[i];  // Update max_ptr if a larger value is found
        }
    }
    return max_ptr;
}
