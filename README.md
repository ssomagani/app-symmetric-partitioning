# Project to demonstrate binding a client instance exclusively to a partition

## Description
The code here demonstrates how to map instances 1-1 with partitions by doing the following -
1. First, get the mapping of partitions to some random partition keys
2. Then, create a HashMap in the application to map the instanceIds with the random partition keys obtained above
3. Finally, use the HashMap created above as the first argument to the stored procedure so that 1-1 mapping is achieved 

## Running Instructions
1. Compile the stored procedure in the db package into a jar
2. Load the compiled jar into VoltDB
3. Update the deployment.xml to set sitesperhost=<instance_count>
4. Run client/Application to populate data by instance 
5. Verify by running the command `exec @Statistics TABLE 0;` in sqlcmd and verify that all the partitions have equal number of rows.
