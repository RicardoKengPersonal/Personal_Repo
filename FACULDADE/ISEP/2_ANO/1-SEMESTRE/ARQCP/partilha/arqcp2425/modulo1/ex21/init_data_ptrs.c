void init_data_ptrs(int *data, int n, int** ptrs)
{
	for (int i = 0; i < n; i++) 
	{
        ptrs[i] = &data[i];  // Assign the address of the data element to the pointer array
    }
}
