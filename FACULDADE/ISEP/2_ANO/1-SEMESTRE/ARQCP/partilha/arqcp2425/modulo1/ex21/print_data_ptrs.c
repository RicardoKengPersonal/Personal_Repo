void print_data_ptrs(int **ptrs, int n) 
{
    for (int i = 0; i < n; i++) 
    {
        printf("ptrs[%d]: address = %p, value = %d\n", i, (void*)ptrs[i], *ptrs[i]);
    }
}
