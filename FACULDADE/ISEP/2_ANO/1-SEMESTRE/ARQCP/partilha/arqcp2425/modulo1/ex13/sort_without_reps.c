int sort_without_reps(short *src, int n, short *dest) 
{
    for (int i = 0; i < n; i++) 
    {
        *(dest + i) = *(src + i);
    }

    
    for (int i = 0; i < n ; i++) 
    {
        for (int j = 0; j < n - 1; j++) 
        {
            if (*(dest + j) > *(dest + j + 1)) 
            {
                int temp = *(dest + j);
                *(dest + j) = *(dest + j + 1);
                *(dest + j + 1) = temp;
            }
        }
    }
    
    int unique_count = 0;

    for (int i = 0; i < n; i++) 
    {
        if (i == n - 1 || *(dest + i) != *(dest + i + 1)) 
        {
            *(dest + unique_count) = *(dest + i);
            unique_count++;
        }
    }
    
    return unique_count;
}

