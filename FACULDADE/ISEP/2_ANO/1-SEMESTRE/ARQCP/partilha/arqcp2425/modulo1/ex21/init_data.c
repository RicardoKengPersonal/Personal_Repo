void init_data(int *data, int n)
{
	    // Seed the random number generator using the current time
    srand(time(NULL));

    // Loop through the array and assign random values
    for (int i = 0; i < n; i++) {
        data[i] = rand(); // Assign a random integer to each element
    }
}
