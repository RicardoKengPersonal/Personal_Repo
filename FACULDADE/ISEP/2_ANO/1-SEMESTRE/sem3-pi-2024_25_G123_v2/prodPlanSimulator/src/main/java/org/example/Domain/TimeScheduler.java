package org.example.Domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the scheduling of time blocks for operations, storing blocks as Timer objects.
 * Each block represents a segment of time available for scheduling operations.
 */
public class TimeScheduler {
    private List<Timer> timerSchedule;
    private int blockDurationMinutes;

    /**
     * Constructs a TimeScheduler with a specified block duration.
     * Initializes the schedule with one block of the specified duration.
     *
     * @param blockDurationMinutes the duration of each time block in minutes
     */
    public TimeScheduler(int blockDurationMinutes) {
        this.blockDurationMinutes = blockDurationMinutes;
        this.timerSchedule = new ArrayList<>();
        addBlock(new Timer(0, 0, blockDurationMinutes));
    }

    /**
     * Returns the current schedule of time blocks.
     *
     * @return the list of scheduled Timer blocks
     */
    public List<Timer> getTimer() {
        return timerSchedule;
    }

    /**
     * Adds a new Timer block to the schedule.
     *
     * @param timer the Timer block to add to the schedule
     */
    public void addBlock(Timer timer) {
        timerSchedule.add(timer);
    }

    /**
     * Checks the schedule to determine the time until the next available block,
     * starting from a given time.
     *
     * @param time the start time to check for availability
     * @return the time until an available block, or -1 if no availability is found
     */
    public int timeUntilAvailable(int time) {

        // Check for availability in existing blocks
        for (Timer timer : timerSchedule) {
            if (timer.startTime >= time && timer.operation == null) {
                return timer.startTime - time;  // Return time until available block
            }
        }

        // Add new blocks if necessary to reach the requested time
        addNewBlocksIfNecessary(time);

        // Re-check the schedule after adding new blocks
        for (Timer timer : timerSchedule) {
            if (timer.startTime >= time && timer.operation == null) {
                return timer.startTime;  // Return the start time of the new block
            }
        }

        // Return -1 if no blocks are available (unlikely if blocks are added properly)
        return -1;
    }

    /**
     * Adds new Timer blocks as needed until a block is available at or after the specified time.
     *
     * @param time the time at which an available block is required
     */
    private void addNewBlocksIfNecessary(int time) {
        Timer lastTimer = timerSchedule.get(timerSchedule.size() - 1); // Get the last block in the schedule

        // Continue adding blocks until an available block is created or time is met
        while (lastTimer.startTime < time || lastTimer.operation != null) {

            int newBlockStart = lastTimer.endTime;  // Start the new block after the last block ends
            int newBlockEnd = newBlockStart + blockDurationMinutes;  // Set the end time of the new block

            // Create and add the new block
            Timer newTimer = new Timer(timerSchedule.size(), newBlockStart, newBlockEnd);
            addBlock(newTimer);

            // Update the last block to the newly added block
            lastTimer = newTimer;
        }
    }
}
