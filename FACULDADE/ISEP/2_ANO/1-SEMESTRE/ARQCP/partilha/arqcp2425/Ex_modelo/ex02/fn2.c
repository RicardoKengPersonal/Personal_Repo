void fn2(int *x)
{
	    // Typecast the integer pointer to a pointer to unsigned char (byte)
    unsigned char *bytes = (unsigned char *)x;
    
    // Swap the first and the fourth byte, and the second and the third byte
    unsigned char temp;
    
    // Swap the first (bytes[0]) and the fourth (bytes[3]) byte
    temp = bytes[0];
    bytes[0] = bytes[3];
    bytes[3] = temp;
    
    // Swap the second (bytes[1]) and the third (bytes[2]) byte
    temp = bytes[1];
    bytes[1] = bytes[2];
    bytes[2] = temp;
}
