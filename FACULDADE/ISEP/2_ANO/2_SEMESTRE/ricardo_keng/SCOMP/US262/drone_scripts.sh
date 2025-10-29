#!/bin/bash

# This script creates the txt files to test the US262 program with random values. It overwrites existing txt files, so no need to delete them, just run the script

# Run the script:
# 1. Run "chmod +x drone_scripts.sh" in the terminal
# 2. Run ./drone_scripts.sh

# Function to generate random float numbers
generate_random_float() {
    echo "scale=2; $RANDOM/32768" | bc
}

# Number of drones and ticks
NUM_DRONES=5
NUM_TICKS=10

# Create the drone text files with random values
for drone in $(seq 1 $NUM_DRONES); do
    echo "Generating script for Drone $drone..."
    > "drone${drone}.txt" # Clear the file if it already exists

    # Generate random positions and speeds for each tick
    for tick in $(seq 1 $NUM_TICKS); do
        # Generate random positions (x, y, z) and speed
        x=$(generate_random_float)
        y=$(generate_random_float)
        z=$(generate_random_float)
        speed=$(($RANDOM % 10 + 1)) # Random speed between 1 and 10

        # Write to the drone's text file in the correct format: x y z speed
        echo "$x $y $z $speed" >> "drone${drone}.txt"
    done
    echo "Generated random values for Drone $drone"
done

echo "Random drone scripts have been generated successfully!"
