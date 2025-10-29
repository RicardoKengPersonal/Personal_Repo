void sort_data_ptrs(int **ptrs, int n) 
{
    // Using a simple bubble sort for demonstration
    for (int i = 0; i < n - 1; i++) 
    {
        for (int j = 0; j < n - i - 1; j++) 
        {
            if (*ptrs[j] < *ptrs[j + 1]) 
            {  
				// Compare the values being pointed to
                // Swap pointers if they are out of order
                int *temp = ptrs[j];
                ptrs[j] = ptrs[j + 1];
                ptrs[j + 1] = temp;
            }
        }
    }
}
