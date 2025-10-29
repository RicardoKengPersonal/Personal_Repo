void frequencies(float *grades, int n, int *freq)
{
	int integer_part = 0;
	
	for(int i = 0; i < 21 ; i++)
	{
		*(freq+1) = 0;
	}
	
	for(int i = 0; i < n; i++)
	{
		integer_part = (int)(*(grades+i));
		
		if(integer_part >= 0 && integer_part <= 20)
		{
			*(freq+integer_part) += 1;
		}
	}
}
