package org.gtech.urlshortner.utils;

public class SnowflakeGenerator {

    private final long machineId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    public SnowflakeGenerator(long machineId) {
        this.machineId = machineId;
    }

    public synchronized long nextId() {
        long currentTimestamp = System.currentTimeMillis();

        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & 4095; // max 4095

            if (sequence == 0) {
                // wait till next millisecond
                while (currentTimestamp == lastTimestamp) {
                    currentTimestamp = System.currentTimeMillis();
                }
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = currentTimestamp;

        return (currentTimestamp << 22) | (machineId << 12) | sequence;
    }
}
