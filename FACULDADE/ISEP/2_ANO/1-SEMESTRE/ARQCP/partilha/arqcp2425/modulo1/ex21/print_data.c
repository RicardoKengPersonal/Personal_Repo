void print_data(int *data, int n)
{
	for(int i = 0; i < n; i++)
	{
		printf("data[%d]= 0x%x\n",i,*data);
		data++;
	}
}
