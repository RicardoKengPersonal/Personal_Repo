int sum_even(int *vec, int n)
{
	int sum = 0;
	
	for (int i = 0; i < n; i++)
	{
		if(*vec % 2 == 0) // Is the number even?
		{
			sum = sum + *vec; // If so , add to the sum
			vec++;
		}
		else
		{
			vec++;
		}
	}
	
	return sum;
}
