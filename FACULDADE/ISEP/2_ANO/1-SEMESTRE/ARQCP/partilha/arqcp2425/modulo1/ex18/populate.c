void populate(unsigned char *vec, int num, int limit)
{
	for (int i = 0; i < num; i++) 
    {
        *vec++ = rand() % (limit + 1);
    }
}
