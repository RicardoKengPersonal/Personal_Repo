int sum_integer_bytes(unsigned int *p)
{
	unsigned char *byte_ptr = (unsigned char *) p;  // Cast to a byte pointer
    int sum = 0;

    for (int i = 0; i < 4; i++) // Loop through each of the 4 bytes of the integer
    {
        sum += byte_ptr[i];  // Access each byte and add to the sum
    }

    return sum;
}
