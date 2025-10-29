void array_sort(int* vec, int n)
{
	for (int i = 0; i < n ; i++) 
	{
        for (int j = 0; j < n - 1; j++) 
        {
            
            if (*(vec + j) > *(vec + j + 1)) 
            {
                
                int temp = *(vec + j);
                *(vec + j) = *(vec + j + 1);
                *(vec + j + 1) = temp;
            }
        }
    }
}

