# RCOMP: Project 1  Sprint 1
### Student : Ricardo Keng Machado da Silva

## 1. General considerations

The development of this project follows the structured cabling rules, aside from specified cases referred
in the Sprint 1 description provided by the client.

For this building the cables used were the CAT7 copper cable for both floors. As for the female connectors, these were 
of the type RJ45. 

## 2. Structured cabling

### 2.1 Outlets 

The outlet placement and calculations all follow the structured cabling rules, aside from specific cases mentioned in
the Sprint 1 description, these rules being:

- Structured cabling standards specify a minimum of two outlets per work area, and also a ratio of two outlets for each 
10 square meters of area. Therefore, for a work area between 10 m^2 and 20m^2, four outlets should be available and for
an area between 20m^2 and 30m^2, six outlets should be available.
- Outlets’ location within the work area should be such that end user equipment is in reach using the up to five meters 
long patch cords and also provide flexibility for users being able to move their equipment. Wherever the user equipment 
is, there should always be an outlet less than three meters away.

### 2.1.1 Exceptions

As mentioned previously, the project Sprint 1 description had exceptions to be followed , these being:

- Floor 0:
  - Room 4.0.10 is a storage area that may be used to house a cross-connect and network infrastructure
    hardware, no network outlets are required in this room, and the same applies to restrooms and shared
    areas like the entrance hall and corridors. 
  - Rooms 4.0.2 has a specific purpose and the only network outlets necessary are two near each floor cable passageway. 
  - Elsewhere, the standard number of network outlets per area ratio is to be enforced.
- Floor 1:
  - Rooms 4.1.17 and 4.1.18 are storage areas that may be used to house a cross-connect and other network
    infrastructure hardware, no network outlets are required there, the same goes for restrooms, and shared
    areas like halls and corridors. 
  - Other identified rooms must be provided with the standard number of network outlets per area ratio.

### 2.1.2 Outlets placement and calculations

# Note: I rounded up above the result in every scenario, making sure the rooms are well equiped. 

- Floor 0:
  - **Room 4.0.1**:
    - Width : 5.33m; Length: 5.33m; Area: 28.40 m^2;
    - Number of outlets: (28.40 / 10) * 2 = 3 * 2 = 6;
  - **Room 4.0.2**:
    - Width : 11.33m; Length: 11.33m; Area: 128.37 m^2;
    - Number of outlets: 2 (Client specified)
  - **Room 4.0.3**: 
    - Width : 3.33m; Length: 6.67m; Area: 22,21 m^2;
    - Number of outlets: (22.21 / 10) * 2 = 3 * 2 = 6;
  - **Room 4.0.4**:
    - Width: 3.33m; Length: 6.67m; Area: 22.21 m^2;
    - Number of outlets: (22.21 / 10) * 2 = 3 * 2 = 6;
  - **Room 4.0.5**:
    - Width: 3.33m; Length: 6.67m; Area: 22.21 m^2;
    - Number of outlets: (22.21 / 10) * 2 = 3 * 2 = 6;
  - **Room 4.0.6**:
    - Width: 6.67m; Length: 6.67m; Area: 44,49 m^2;
    - Number of outlets: (44.49 / 10) * 2 = 5 * 2 = 10;
  - **Room 4.0.7**:
    - Width: 3.33m; Length: 6.67m; Area: 22.21 m^2;
    - Number of outlets: (22.21 / 10) * 2 = 3 * 2 = 6;
  - **Room 4.0.8**:
    - Width: 3.33m; Length: 6.67m; Area: 22.21 m^2;
    - Number of outlets:(22.21 / 10) * 2 = 3 * 2 = 6;
  - **Room 4.0.9**:
    - Width: 7.00m; Length: 6.67m; Area: 46.69 m^2;
    - Number of outlets: (46.69 / 10) * 2 = 5 * 2 = 10;
  - **Room 4.0.10**:
    - Width: 4.00m; Length: 3.33m; Area: 13.32 m^2;
    - Number of outlets: 0 (Client specified);

- Total Outlets for floor 0: 58

![Floor 0 outlets](Building%204%20-%20Floor%200%20-%20Outlets.png)
![Floor 0 Sizes Table](Building%204%20-%20Floor%200%20-%20Sizes.png)
![Floor 0 Measurements Plant](Building%204%20-%20Floor%200%20-%20measurements.png)

# Note: Since room 4.1.1 has exaclty 50.00 m^2 of area, following the rule, the number of outlets should be
10, but since all the other rooms were rounder up, I considered good sense to apply 2 more outlets to this room.

- Floor 1: 
  - **Room 4.1.1**:
    - Width: 10.00m; Length: 5.00m ; Area: 50.00 m^2;
    - Number of outlets: (50.00 / 10) * 2 = 6 * 2 = 12;
  - **Room 4.1.2**:
    - Width: 4.00m ; Length: 8.00m; Area: 32.00 m^2;
    - Number of outlets: (32.00 / 10) * 2 = 4 * 2 = 8;
  - **Room 4.1.3**:
    - Width: 4.00m; Length: 6.33m; Area: 25.32 m^2;
    - Number of outlets: (25.32 / 10) * 2 = 3 * 2 = 6;
  - **Room 4.1.4**:
    - Width: 4.33m; Length: 9.33m; Area: 40.40 m^2;
    - Number of outlets: (40.40 / 10) * 2 = 5 * 2 = 10;
  - **Room 4.1.5**:
    - Width: 4.00m; Length: 6.67m; Area: 26.68 m^2;
    - Number of outlets: (26.68 / 10) * 2 = 3 * 2 = 6;
  - **Room 4.1.6**:
      - Width: 4.00m; Length: 6.67m; Area: 26.68 m^2;
      - Number of outlets: (26.68 / 10) * 2 = 3 * 2 = 6;
  - **Room 4.1.7**:
      - Width: 4.00m; Length: 6.67m; Area: 26.68 m^2;
      - Number of outlets: (26.68 / 10) * 2 = 3 * 2 = 6;
  - **Room 4.1.8**:
      - Width: 4.00m; Length: 6.67m; Area: 26.68 m^2;
      - Number of outlets: (26.68 / 10) * 2 = 3 * 2 = 6;
  - **Room 4.1.9**:
      - Width: 8.00m; Length: 6.67m; Area: 53.36 m^2;
      - Number of outlets: (53.36 / 10) * 2 = 6 * 2 = 12;
  - **Room 4.1.10**:
      - Width: 4.00m; Length: 8.00m; Area: 32.00 m^2;
      - Number of outlets: (32.00 / 10) * 2 = 4 * 2 = 8;
  - **Room 4.1.11**:
      - Width: 4.00m; Length: 8.00m; Area: 32.00 m^2;
      - Number of outlets: (32.00 / 10) * 2 = 4 * 2 = 8;
  - **Room 4.1.12**:
      - Width: 4.00m; Length: 5.67m; Area: 22.68 m^2;
      - Number of outlets: (22.68 / 10) * 2 = 3 * 2 = 6;
  - **Room 4.1.13**:
      - Width: 4.00m; Length: 5.67m; Area: 22.68 m^2;
      - Number of outlets: (22.68 / 10) * 2 = 3 * 2 = 6;
  - **Room 4.1.14**:
      - Width: 4.00m; Length: 11.33m; Area: 45.32 m^2;
      - Number of outlets: (45.32 / 10) * 2 = 5 * 2 = 10;
  - **Room 4.1.15**:
      - Width: 4.00m; Length: 5.67m; Area: 22.68 m^2;
      - Number of outlets: (22.68 / 10) * 2 = 3 * 2 = 6;
  - **Room 4.1.16**:
      - Width: 4.00m; Length: 6.67m; Area: 22.68 m^2;
      - Number of outlets: (22.68 / 10) * 2 = 3 * 2 = 6;
  - **Room 4.1.17**:
      - Width: 2.00m; Length: 5.00m; Area: 10.00 m^2;
      - Number of outlets: 0 (Client specified)
  - **Room 4.1.18**:
      - Width: 1.67m; Length: 6.33m; Area: 10.57 m^2;
      - Number of outlets: 0 (Client specified)

- Total number of Outlets: 122 + 2  = 124 (since there are two AP's placed in the halls that need dedicated network outlets) 

  ![Floor 1 outlets](Building%204%20-%20Floor%201%20-%20Outlets.png)
  ![Floor 1 Sizes Table](Building%204%20-%20Floor%201%20-%20Sizes.png)
  ![Floor 1 Measurements Plant](Building%204%20-%20Floor%201%20-%20measurements.png)

### 2.2 Intermediate Cross-Connect (ICC) And Horizontal Cross-Connect (HCC)

## 2.2.1 Intermediate Cross Connect (ICC)
- 1 per building.
- The ICC can be housed in the same room and rack as the HC for that floor.

## 2.2.2 Horizontal Cross Connect (HCC)
- 1 per floor.
- Should be centrally located relative to the outlets they serve.
- No outlet should be more than 80 meters in a straight line from the HC.
- Cable length must not exceed 90 meters.
- Consolidation Points (CPs) may be created if needed.
- A cross-connect per floor is not mandatory—if there are only a few outlets, a single HC may serve multiple floors.

## 2.2.3 Location for HCC and ICC for Building 4
# Floor 0

The document provided by the client suggested that for floor 0 of building 4, the room 4.0.10 would be perfect for this use.
Given this information I decided to place there the ICC for the whole building, as per building there only needs to be one ICC.
Since the ICC was placed there, I decided to also place the HCC in the same rack.

# Floor 1 

The document provided by the client suggested that, for this floor, since the rooms 4.1.17 and 4.1.18 were storage areas, 
then these rooms would reunite the conditions to house cross-connect and other network infrastucture. Since the building
only requires 1 ICC per floor, then this floor only requires the placement of 1 HCC. I decided to place the HCC in the 
room 4.1.18.

### 2.3 Consolidation Points (CP)

For the placement / creation of Consolidation Points (CP), I used calculations in order to decide how many of them were 
to be created and their respective placement. First, I decided to use 24-port patch panels since it's one of the most common
patch panels and allowed me to distribute equally the outlets per patch panel for each floor. I also tried that, for each
CP, there would be around 20 connections to it. For this, I used the following calculations:

        - Number of outlets / 24 = Number of patch panels needed.

# Floor 0

    - Total number of outlets: 58

Calculations:
    - 58 / 24 = 2,41 -> Rounded up to 3 24-port patch panels.

# Floor 1

    - Total number of outlets: 124

Calculations:
    - 124 / 24 = 5,16 -> Rounded up to 6 24-port patch panels

These calculations take in mind that there could be future upgrades, that way, while rounding up the calculations we can 
leave space for these unplanned future upgrades.

## 2.3.1 Placement
# Floor 0

With the number of patch panels needed we can now decide where to place each one, in order to serve around the same number 
of outlets. For floor 0, I placed one CP in the room 4.0.9, serving the rooms 4.0.9, 4.0.8 and 4.0.7, totalling a number of 
22 outlets served.

The second CP was placed in the room 4.0.6, serving the rooms 4.0.6, 4.0.5 and 4.0.4, totalling a number of 22 outlets served.

The third and final CP was placed in the room 4.0.2, serving the rooms 4.0.2, 4.0.3 and 4.0.1, totalling a number of 14 
outlets served.

Since the calculations take into account the possibility of future upgrades, this floor is prepared for 14 new outlets
since not all the patch panels are fully used.

# Floor 1 

With the number of patch panels previously calculated, we can now decide where to place each CP in order for them to serve
around the same number of outlets. For this floor, the calculations indicate 6 24-port patch panels. For this floor I 
tried to place each CP in order for all of them to serve around 20 outlets each.

The first CP is in the room 4.1.2, serving the rooms 4.1.1 and 4.1.2, totalling a number of 21 outlets served.

The second CP is in the room 4.1.4, serving the rooms 4.1.3,4.1.4 and 4.1.5, totalling a number of 22 outlets served.

The third CP is in the room 4.1.7, serving the rooms 4.1.6,4.1.7 and 4.1.8, totalling a number of 18 outlets served.

The fourth CP is in the room 4.1.9, serving the rooms 4.1.9 and 4.1.14, totalling a number of 23 outlets served.

The fifth CP is in the room 4.1.13, serving the rooms 4.1.12,4.1.13,4.1.15 and 4.1.16, totalling a number of 24 outlets 
served.

The sixth and final CP is in the room 4.1.11, serving the rooms 4.1.11 and 4.1.10, totalling a number of 16 outlets.


## 2.4 Cross-Connection
# 2.4.1 Sum up for each floor

# Floor 0

|             Material             | How much is needed... |                                                                                                   Why?                                                                                                   |
|:--------------------------------:|:---------------------:|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
|     Main Cross-Connect (MCC)     |           0           |                                                                                The MCC will be located in the datacenter.                                                                                |
|  Horizontal Cross-Connect (HCC)  |           1           |                                                                                  Only 1 HCC is required for each floor.                                                                                  |  
| Intermediate Cross-Connect (ICC) |           1           |                                                                                 Only 1 ICC is required for each building                                                                                 |   
|     Consolidation Point (CP)     |           3           |      I defined that 1 CP will be connected to 24 outlets. Since I have 58 outlets in total, I will need 3 CP. There is space left to connect 14 new outlets in the future if the number increases.       |   
|        Access Point (AP)         |           2           | Since the building is one of the bigger buildings of the campus and houses plenty devices, I decided to place 2 AP for this floor to guarantee great connection at all times and places for this floor.  |   

# Floor 1

|             Material             | How much is needed... |                                                                                                  Why?                                                                                                   |
|:--------------------------------:|:---------------------:|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
|     Main Cross-Connect (MCC)     |           0           |                                                                               The MCC will be located in the datacenter.                                                                                |
|  Horizontal Cross-Connect (HCC)  |           1           |                                                                                 Only 1 HCC is required for each floor.                                                                                  |  
| Intermediate Cross-Connect (ICC) |           0           |                                                                                Only 1 ICC is required for each building                                                                                 |   
|     Consolidation Point (CP)     |           3           |     I defined that 1 CP will be connected to 24 outlets. Since I have 122 outlets in total, I will need 6 CP. There is space left to connect 22 new outlets in the future if the number increases.      |   
|        Access Point (AP)         |           3           | Since the building is one of the bigger buildings of the campus and houses plenty devices, I decided to place 3 AP for this floor to guarantee great connection at all times and places for this floor. |   

### 2.4.2 Patch Panels/ Switches

## Patch Panels
Each cable reaching a cross-connect it attached to an appropriate type (copper or fiber) patch panel, the number of patch panels needed at each cross-connect depends on the number of connections supported for each.
- Copper panels (24 or 48 connections, taking 1U or 2U respectively);
- Fiber panels (more vendor specific);

Patch panel models must be selected, and comfortingly, the number of patch panels required at each cross-connect is determined.

Because structured cabling infrastructure is supposed to bear future hardware upgrades and additions, an extra 100% over-dimensioning should be applied.

In engineering dimensioning, when some value is reached through calculations, then the commercially available solutions that supports that value must be selected.
Examples:
- If the telecommunications enclosure is housing a single 1U patch panel, then we add another 1U for
  the expected corresponding switch, making 2U, and an additional 100% over dimensioning, this will
  make 4U total. Commercially available telecommunications enclosures start at 6U, so we will use one of
  those.
- If the telecommunications enclosure is housing 2U of patch panels, then we add another 2U for the
  expected corresponding switches, making 4U, and an additional 100% over dimensioning, this will make
  8U. Commercially available size above 6U is usually 12U, so we will use one.

## Switches
- Each switch occupies the same space as the corresponding patch panels
- 
### **Patch Panels** ###

Floor 0:
1 patch panel 24 ports to HCC 
1 patch panel 24 ports to ICC 
1 patch panel 24 ports to each CP (copper)

Total:
- 5 patch panels 24 ports 

Floor 1:
1 patch panel 24 ports to HCC
1 patch panel 24 ports to each CP (copper)

Total:
- 7 patch panels 24 ports

### **Switch** ###

Floor 0:

- 5 switches

Floor 1:

- 7 switches


### **Rack** ###

Floor 0:
- 5x 24 port patch panels -> 5U
- switch: 5U

total: 10U (100% oversize)

- 10U for active equipment
  Total: 20U ----> 40U (oversize 100%) ----> 42U (commercial value)

Floor 1:
- 7x 24 port patch panels -> 7U
- switch: 7U

Total: 28U

- 28U for active equipment
- Total: 28U -----> 56U (100% oversize) ----> 58U


### 2.5 Fiber Cables 

# Floor 0

The fiber cable needed for this floor is:

1.00 + 2.00 = 3.00 m 

These calculations were made following the sketch I provided, accounting for future upgrades it's wise to have some more cable.
I considered that an additional 0.5 meters of fiber cable would be enough for future upgrades.

Total : 3.00 + 0.5 = 3.5 meters

# Floor 1

The fiber cable needed for this floor is:

4.00 (since this is the ceiling height of the floor below, and the connection from the ICC on floor 0 to the HCC in floor 1
is made vertically we need to account for this measure.)

The rest of the measurements were made through the sketch I provided.

Calculations: 4.00 + 1.67 + 1.67 = 7.34 m 

Accounting for future upgrades, I decided that an additional 0.5m of fiber cable would be enough:

Total: 7.34 + 0.5 = 7.84 meters

### 2.6 Copper Cables

# Floor 0 

![Building 4 Floor 0 Outlets](Building%204%20-%20Floor%200%20-%20Outlets.png)

Note: A height of 0.5m above the ground was considered since the outlets aren't exactly on the ground.

A CP was placed in the room 4.0.2, serving the rooms 4.0.2, 4.0.3 and 4.0.1, totalling a number of 14
outlets served.

Distance from HCC to CP : 1.67 + 2.67 + 29.33 + 18.33 = 52m (summed only once for the rooms served by the CP)

Room 4.0.1:
- Outlet 1 : 8.33 + 11.67 + 3.67 + 0.5 = 24.17m
- Outlet 2 : 8.33 + 11.67 + 1.67 + 0.5 = 26.17m 
- Outlet 3 : 8.33 + 11.67 + 1.67 + 0.5 = 26.17m 
- Outlet 4 : 8.33 + 11.67 + 3.67 + 0.5 = 24.17m
- Outlet 5 : 8.33 + 11.67 + 5.00 + 1.67 + 0.5 = 27.17m
- Outlet 6 : 8.33 + 11.67 + 5.00 + 3.34 + 0.5 = 28.84m

Total: 24.17 + 26.17 + 26.17 + 24.17 + 27.17 + 28.84 = 156.69 m

Room 4.0.2:
- Outlet 1 : 3.00 + 0.5 = 3.5m
- Outlet 2 : 8.33 + 5 + 0.5 = 13.83m

Total: 3.5 + 13.83 = 17.33m

Room 4.0.3:
- Outlet 1 : 6.67 + 5.00 + 0.5 = 12.17m
- Outlet 2 : 6.67 + 2.00 + 0.5 = 9.17m
- Outlet 3 : 6.67 + 1.00 + 0.5 = 8.17m
- Outlet 4 : 6.67 + 2.00 + 0.5 = 9.17m
- Outlet 5 : 6.67 + 3.00 + 2.00 + 0.5 = 12.17m 
- Outlet 6 : 6.67 + 3.00 + 3.00 + 0.5 = 13.17m

Total: 12.17 + 9.17 + 8.17 + 9.17 + 12.17 + 13.17 = 64.02m

Total for the three rooms: 52 + 156.69 + 17.33 + 64.02 = 290.04m

The second CP was placed in the room 4.0.6, serving the rooms 4.0.6, 4.0.5 and 4.0.4, totalling a number of 22 outlets served.

Distance from HCC to CP: 1.67 + 2.67 + 29.33 = 33.67m

Room 4.0.4:
- Outlet 1 : 7.00 + 4.67 + 0.5 = 12.17m 
- Outlet 2 : 7.00 + 2.00 + 0.5 = 9.5m
- Outlet 3 : 7.00 + 1.00 + 0.5 = 8.5m
- Outlet 4 : 7.00 + 2.00 + 0.5 = 9.5m 
- Outlet 5 : 7.00 + 3.00 + 2.00 + 0.5 = 12.5m
- Outlet 6 : 7.00 + 3.00 + 5.00 + 0.5 = 15.5m

Total: 12.17 + 9.5 + 8.5 + 9.5 + 12.5 + 15.5 = 67.67m

Room 4.0.5:
- Outlet 1 : 3.67 + 5.00 + 0.5 = 9.17m
- Outlet 2 : 3.67 + 2.00 + 0.5 = 6.17m
- Outlet 3 : 3.67 + 1.00 + 0.5 = 5.17m
- Outlet 4 : 3.67 + 2.00 + 0.5 = 6.17m
- Outlet 5 : 3.67 + 2.00 + 2.67 + 0.5 = 8.84m 
- Outlet 6 : 3.67 + 5.00 + 2.67 + 0.5 = 11.84m

Total: 9.17 + 6.17 + 5.17 + 6.17 + 8.84 + 11.84 = 47.36m

Room 4.0.6:
- Outlet 1 : 3.33 + 6.33 + 3.33 + 0.5 = 13.49m 
- Outlet 2 : 3.33 + 6.33 + 1.67 + 0.5 = 11.83m
- Outlet 3 : 3.33 + 5.00 + 0.5 = 8.83m
- Outlet 4 : 3.33 + 3.00 + 0.5 = 6.83m
- Outlet 5 : 3.33 + 1.33 + 0.5 = 5.16m
- Outlet 6 : 1.67 + 0.5 = 2.17m
- Outlet 7 : 1.33 + 0.5 = 1.83m
- Outlet 8 : 3.33 + 1.33 + 0.5 = 5.16m 
- Outlet 9 : 3.33 + 3.00 + 0.5 = 6.83m
- Outlet 10 : 3.33 + 5.00 + 0.5 = 8.83m

Total: 13.49 + 11.83 + 8.83 + 6.83 + 5.16 + 2.17 + 1.83 + 5.16 + 6.83 + 8.83 = 71.96m

Total for the three rooms: 33.67 + 67.67 + 47.36 + 71.96 = 220.66m

A CP in the room 4.0.9, serving the rooms 4.0.9, 4.0.8 and 4.0.7, totalling a number of 22 outlets served.

Distance from the HCC to CP : 1.67 + 2.67 + 17.33 = 21.67m

Room 4.0.7:
- Outlet 1 : 7.33 + 5.00 + 0.5 = 12.83m
- Outlet 2 : 7.33 + 2.00 + 0.5 = 9.83m
- Outlet 3 : 8.33 + 0.5 = 8.83m
- Outlet 4 : 9.66 + 0.5 = 10.16m
- Outlet 5 : 10.33 + 2.00 + 0.5 = 12.83m 
- Outlet 6 : 10.33 + 5.00 + 0.5 = 15.83m

Total: 12.83 + 9.83 + 8.83 + 10.16 + 12.83 + 15.83 = 70.31m

Room 4.0.8:
- Outlet 1 : 3.67 + 5.00 + 0.5 = 9.17m  
- Outlet 2 : 3.67 + 2.00 + 0.5 = 6.17m
- Outlet 3 : 4.67 + 0.5 = 5.17m
- Outlet 4 : 5.67 + 0.5 = 6.17m
- Outlet 5 : 6.33 + 2.00 + 0.5 = 8.83m 
- Outlet 6 : 6.33 + 5.00 + 0.5 = 11.83m
- 
Total: 9.17 + 6.17 + 5.17 + 6.17 + 8.83 + 11.83 = 47.34m

Room 4.0.9:
- Outlet 1 : 1.00 + 0.5 = 1.5m
- Outlet 2 : 3.33 + 1.67 + 0.5 = 5.5m 
- Outlet 3 : 3.33 + 3.33 + 0.5 = 7.16m
- Outlet 4 : 3.33 + 5.00 + 0.5 = 8.83m
- Outlet 5 : 3.33 + 6.33 + 1.67 + 0.5 = 11.83m 
- Outlet 6 : 3.33 + 6.33 + 3.33 + 0.5 = 13.49m 
- Outlet 7 : 1.00 + 0.5 = 1.5m
- Outlet 8 : 3.33 + 1.67 + 0.5 = 5.5m
- Outlet 9 : 3.33 + 3.33 + 0.5 = 7.16m
- Outlet 10 : 3.33 + 5.00 + 0.5 = 8.83m

Total: 1.5 + 5.5 + 7.16 + 8.83 + 11.83 + 13.49 + 1.5 + 5.5 + 7.16 + 8.83 = 71.3m

Total for the three rooms: 71.3 + 47.34 + 70.31 + 21.67 = 210.62m

| Room Nº | How much is needed... |
|:-------:|:---------------------:|
|  4.0.1  |       156.69 m        |
|  4.0.2  |        17.33m         |
|  4.0.3  |        64.02m         |
|  4.0.4  |        67.67m         |
|  4.0.5  |        47.36m         |
|  4.0.6  |        71.96m         |
|  4.0.7  |        70.31m         |
|  4.0.8  |        47.34m         |
|  4.0.9  |         71.3m         |

|   Material   | How much is needed... |
|:------------:|:---------------------:|
| Copper cable |       721,32 m        | 

# Floor 1

The first CP is in the room 4.1.2, serving the rooms 4.1.1 and 4.1.2, totalling a number of 20 outlets served.

Distance from HCC to CP : 4.33 + 1.67 + 23.33 + 30.00 + 16.67 = 76m

Room 4.1.1:
- Outlet 1: 6.00 + 10.33 + 5.33 + 3.33 + 2.00 = 26.99m
- Outlet 2: 6.00 + 10.33 + 5.33 + 1.67 + 2.00 = 25.33m
- Outlet 3: 6.00 + 10.33 + 3.67 + 2.00 = 22.00m
- Outlet 4: 6.00 + 10.33 + 1.67 + 2.00 = 20.00m
- Outlet 5: 6.00 + 8.00 + 2.00 = 16.00m
- Outlet 6: 6.00 + 6.00 + 2.00 = 14.00m
- Outlet 7: 6.00 + 3.67 + 2.00 = 11.67m
- Outlet 8: 6.00 + 1.67 + 2.00 = 9.67m
- Outlet 9: 4.67 + 2.00 = 6.67m
- Outlet 10: 2.33 + 2.00 = 4.33m
- Outlet 11: 0.67 + 1.67 + 2.00 = 4.34m
- Outlet 12: 0.67 + 3.33 + 2.00 = 6.00m

Total: 167.00m

Room 4.1.2:
- Outlet 1: 7.67 + 2.00 + 2.00 = 11.67m
- Outlet 2: 6.00 + 2.00 = 8.00m
- Outlet 3: 3.67 + 2.00 = 5.67m
- Outlet 4: 1.33 + 2.00 = 3.33m
- Outlet 5: 0.67 + 1.33 + 2.00 = 4.00m 
- Outlet 6: 0.67 + 2.67 + 2.00 = 5.34m
- Outlet 7: 0.67 + 4.00 + 2.00 + 2.00 = 8.67m 
- Outlet 8: 0.67 + 4.00 + 4.33 + 2.00 = 11.00m

Total: 57.68m

Total for the two rooms: 57.68 + 167.00 + 76.00 = 300.68m

The second CP is in the room 4.1.4, serving the rooms 4.1.3,4.1.4 and 4.1.5, totalling a number of 22 outlets served.

Distance from HCC to CP: 4.33 + 1.67 + 23.33 + 30.00 + 5.00 = 64.33m

Room 4.1.3:
- Outlet 1: 11.33 + 1.67 + 2.00 = 15.00m
- Outlet 2: 9.33 + 2.00 = 11.33m
- Outlet 3: 6.67 + 2.00 = 8.67m
- Outlet 4: 4.67 + 2.00 + 2.00 = 8.67m 
- Outlet 5: 4.00 + 1.67 + 2.00 = 7.67m
- Outlet 6: 4.00 + 3.33 + 2.00 = 9.33m

Total: 15.00 + 11.33 + 8.67 + 8.67 + 7.67 + 9.33 = 60.67m

Room 4.1.4:
- Outlet 1: 5.00 + 4.67 + 6.00 + 2.00 = 17.67m
- Outlet 2: 5.00 + 4.67 + 4.00 + 2.00 = 15.67m
- Outlet 3: 5.00 + 4.67 + 2.00 + 2.00 = 13.67m
- Outlet 4: 5.00 + 3.33 + 2.00 = 10.33m
- Outlet 5: 5.00 + 1.67 + 2.00 = 8.67m
- Outlet 6: 3.33 + 2.00 = 5.33m
- Outlet 7: 1.00 + 2.00 = 3.00m
- Outlet 8: 1.00 + 2.00 = 3.00m
- Outlet 9: 3.33 + 2.00 = 5.33m
- Outlet 10: 4.67 + 2.33 + 2.00 = 9.00m 

Total: 17.67 + 15.67 + 13.67 + 10.33 + 8.67 + 5.33 + 3.00 + 3.00 + 5.33 + 9.00 = 91.67m

Room 4.1.5:
- Outlet 1: 5.00 + 9.00 + 7.00 + 1.33 + 2.0 = 24.33m
- Outlet 2: 5.00 + 9.00 + 4.67 + 2.00 = 20.67m
- Outlet 3: 5.00 + 9.00 + 2.00 + 2.00 = 18.00m
- Outlet 4: 5.00 + 6.67 + 2.00 = 13.67m
- Outlet 5: 5.00 + 5.00 + 2.00 + 2.00 = 14.00m 
- Outlet 6: 5.00 + 5.00 + 4.67 + 2.00 = 16.67m

Total: 24.33 + 20.67 + 18.00 + 13.67 + 14.00 + 16.67 = 107.34m

Total for the three rooms: 64.33 + 60.67 + 91.67 + 107.34 = 324.01m

The third CP is in the room 4.1.7, serving the rooms 4.1.6,4.1.7 and 4.1.8, totalling a number of 18 outlets served.

Distance from HCC to CP = 4.33 + 1.67 + 23.33 + 13.33 = 42.66m

Room 4.1.6:
- Outlet 1: 7.67 + 7.00 + 1.33 + 2.00 = 18.00m
- Outlet 2: 7.67 + 4.67 + 2.00 = 14.34m
- Outlet 3: 7.67 + 2.00 + 2.00 = 11.67m
- Outlet 4: 5.67 + 2.00 = 7.67m
- Outlet 5: 3.67 + 2.00 + 2.00 = 7.67m 
- Outlet 6: 3.67 + 4.67 + 2.00 = 10.34m

Total: 18.00 + 14.34 + 11.67 + 7.67 + 7.67 + 10.34 = 69.69m

Room 4.1.7:
- Outlet 1: 0.67 + 7.00 + 4.67 + 2.00 = 14.34m
- Outlet 2: 0.67 + 4.67 + 2.00 = 7.34m
- Outlet 3: 0.67 + 2.00 + 2.00 = 4.67m
- Outlet 4: 1.33 + 2.00 = 3.33m
- Outlet 5: 3.33 + 2.00 + 2.00 = 7.33m 
- Outlet 6: 3.33 + 4.67 + 2.00 = 10.00m

Total: 14.34 + 7.34 + 4.67 + 3.33 + 7.33 + 10.00 = 47.01m

Room 4.1.8:
- Outlet 1: 0.67 + 7.00 + 4.67 + 2.00 = 14.34m 
- Outlet 2: 0.67 + 4.67 + 2.00 = 7.34m
- Outlet 3: 0.67 + 2.00 + 2.00 = 4.67m 
- Outlet 4: 2.67 + 2.00 = 4.67m
- Outlet 5: 4.67 + 2.00 + 2.00 = 8.67m 
- Outlet 6: 4.67 + 4.67 + 2.00 = 11.34m

Total: 14.34 + 7.34 + 4.67 + 4.67 + 8.67 + 11.34 = 51.03m

Total for the three rooms: 51.03 + 47.01 + 69.69 + 42.66 = 210.39m

The fourth CP is in the room 4.1.9, serving the rooms 4.1.9 and 4.1.14, totalling a number of 22 outlets served.

Distance from HCC to CP: 4.33 + 1.67 + 20.00 = 26.00m

Room 4.1.9:
- Outlet 1: 3.67 + 5.00 + 2.00 = 10.67m
- Outlet 2: 3.67 + 3.33 + 2.00 = 9.00m
- Outlet 3: 3.67 + 1.33 + 2.00 = 7.00m
- Outlet 4: 1.33 + 2.00 = 3.33m
- Outlet 5: 1.33 + 2.00 = 3.33m
- Outlet 6: 3.33 + 1.33 + 2.00 = 6.66m 
- Outlet 7: 3.33 + 3.00 + 2.00 = 8.33m
- Outlet 8: 3.33 + 4.67 + 2.00 = 10.00m
- Outlet 9: 3.33 + 6.67 + 2.00 = 12.00m
- Outlet 10: 3.33 + 8.33 + 1.67 + 2.00 = 15.33m 
- Outlet 11: 3.33 + 8.33 + 3.33 + 2.00 = 17.00m
- Outlet 12: 3.33 + 8.33 + 5.67 + 2.0 = 19.33m

Total: 121.65m

Room 4.1.14:
- Outlet 1: 20.00 + 1.33 + 2.00 = 23.33m
- Outlet 2: 18.00 + 2.00 = 20.00m
- Outlet 3: 15.33 + 2.00 = 17.33m
- Outlet 4: 12.67 + 2.00 = 14.67m
- Outlet 5: 10.67 + 2.00 = 12.67m
- Outlet 6: 8.33 + 1.33 + 2.00 = 11.66m
- Outlet 7: 20.00 + 4.00 + 9.33 + 2.0 = 35.33m 
- Outlet 8: 20.00 + 4.00 + 7.00 + 2.0 = 33.00m
- Outlet 9: 20.00 + 4.00 + 4.33 + 2.0 = 30.33m
- Outlet 10: 20.00 + 4.00 + 2.33 + 2.0 = 28.33m

Total: 216.66m

Total for the two rooms: 216.66 + 121.65 + 26.00 = 364.31m

The fifth CP is in the room 4.1.13, serving the rooms 4.1.12,4.1.13,4.1.15 and 4.1.16, totalling a number of 24 outlets
served.

Distance from the HCC to CP: 4.67 + 2.67 + 3.33 + 6.00 = 16.67m

Room 4.1.12:
- Outlet 1: 5.00 + 6.00 + 1.33 + 2.00 = 14.33m
- Outlet 2: 5.00 + 3.67 + 2.00 = 10.67m
- Outlet 3: 5.00 + 1.33 + 2.00 = 8.33m
- Outlet 4: 3.00 + 2.00 = 5.00m
- Outlet 5: 1.00 + 1.33 + 2.00 = 4.33m
- Outlet 6: 1.00 + 3.67 + 2.00 = 6.67m

Total: 51.67m

Room 4.1.13:
- Outlet 1: 3.33 + 6.00 + 1.33 + 2.00 = 12.66m
- Outlet 2: 3.33 + 3.67 + 2.00 = 9.00m
- Outlet 3: 3.33 + 1.33 + 2.00 = 6.66m
- Outlet 4: 1.00 + 2.00 = 3.00
- Outlet 5: 1.00 + 1.33 + 2.00 = 4.33m
- Outlet 6: 1.00 + 3.67 + 2.00 = 6.67m

Total: 42.32m

Room 4.1.15:
- Outlet 1: 5.00 + 6.00 + 1.33 + 2.00 = 14.33m
- Outlet 2: 5.00 + 3.67 + 2.00 = 10.67m
- Outlet 3: 5.00 + 1.33 + 2.00 = 8.33m
- Outlet 4: 3.00 + 2.00 = 5.00m
- Outlet 5: 1.00 + 1.33 + 2.00 = 4.33m
- Outlet 6: 1.00 + 3.67 + 2.00 = 6.67m

Total: 51.67m

Room 4.1.16:
- Outlet 1: 3.33 + 6.00 + 1.33 + 2.00 = 12.66m
- Outlet 2: 3.33 + 3.67 + 2.00 = 9.00m
- Outlet 3: 3.33 + 1.33 + 2.00 = 6.66m
- Outlet 4: 1.00 + 2.00 = 3.00
- Outlet 5: 1.00 + 1.33 + 2.00 = 4.33m
- Outlet 6: 1.00 + 3.67 + 2.00 = 6.67m

Total: 42.32m

Total for the four rooms: 42.32 + 51.67 + 42.32 + 51.67 + 16.67 = 204.68m

The sixth and final CP is in the room 4.1.11, serving the rooms 4.1.11 and 4.1.10, totalling a number of 16 outlets.

Distance from HCC to CP: 4.67 + 2.67 + 3.33 + 11.00 + 6.00 + 5.67 = 33.34m

Room 4.1.10:
- Outlet 1: 5.00 + 8.33 + 1.67 + 2.00 = 17.00m
- Outlet 2: 0.67 + 6.33 + 2.00 = 9.00m
- Outlet 3: 0.67 + 4.00 + 2.00 = 6.67m
- Outlet 4: 0.67 + 2.00 + 2.00 = 4.67m 
- Outlet 5: 2.67 + 2.00 = 4.67m
- Outlet 6: 5.00 + 2.00 + 2.00 = 9.00m 
- Outlet 7: 5.00 + 4.00 + 2.00 = 11.00m
- Outlet 8: 5.00 + 6.33 + 2.00 = 13.33m

Total: 75.34m

Room 4.1.11:
- Outlet 1: 5.00 + 8.33 + 1.67 + 2.00 = 17.00m
- Outlet 2: 3.33 + 6.33 + 2.00 = 11.66m
- Outlet 3: 3.33 + 4.00 + 2.00 = 9.33m
- Outlet 4: 3.33 + 2.00 + 2.00 = 7.33m
- Outlet 5: 1.33 + 2.00 = 3.33m
- Outlet 6: 0.67 + 2.00 + 2.00 = 4.67m
- Outlet 7: 0.67 + 4.00 + 2.00 = 6.67m
- Outlet 8: 0.67 + 6.33 + 2.00 = 9.00m

Total: 68.00m

Total for the two rooms : 68.00 + 75.34 + 33.34 = 176.68m

| Room Nº | How much is needed... |
|:-------:|:---------------------:|
|  4.1.1  |       167.00 m        |
|  4.1.2  |        57.68 m        |
|  4.1.3  |        60.67 m        |
|  4.1.4  |        91.67 m        |
|  4.1.5  |       107.34 m        |
|  4.1.6  |        69.69 m        |
|  4.1.7  |        47.01 m        |
|  4.1.8  |        51.03 m        |
|  4.1.9  |       121.65 m        |
| 4.1.10  |        75.34 m        |
| 4.1.11  |        68.00 m        |
| 4.1.12  |        51.67 m        |
| 4.1.13  |        42.32 m        |
| 4.1.14  |       216.66 m        |
| 4.1.15  |        51.67 m        |
| 4.1.16  |        42.32 m        |
| 4.1.17  |          0 m          |
| 4.1.18  |          0 m          |

|   Material   | How much is needed... |
|:------------:|:---------------------:|
| Copper cable |       1580.68 m       |


# 2.7 Extra cables

Since for floor 1 there are two access points outside rooms we need to place network outlets dedicated to these devices, 
so, we also need to account for the cabling of these outlets. The most-left access point, near the room 4.1.1 can be served 
with the CP (Consolidation Point) in the room 4.1.2, since it's only serving 20 outlets. With this extra added , the CP now serves
21 outlets still leaving room for future upgrades.

Outlet 1: 1.00 + 10.33 + 1.00 + 4.00 = 16.33m

Because there is another AP (access point) in a hall, in this same floor we also need to add an outlet to serve this device.
The CP (Consolidation Point) that's located in the room 4.1.9 can serve this outlet, since it's housing 22 outlets it has space
for two more connections. With this extra, there still is room for future upgrades. Having this implication means the CP now serves
23 outlets instead of the 22 outlets accounted previously.

Outlet 1: 6.00m 

Total: 16.33 + 6.00 = 22.33 m

|   Material   | How much is needed... |
|:------------:|:---------------------:|
| Copper cable |        22.33m         |

Considering the HCC of this floor is on the ground we need to account the cable that's going up to the ceiling (with a height of 2.5 meters)
and then down again for each outlet, not forgetting that the outlets placement is 0.5m above the ground. Although these rules have 
already been accounted for while making the calculations (documented above) it's still important to leave these clarifications
specified in the document. 

# 2.8 Access Points placement and Quantity

Considering the building's size and predicting more or less the expected devices it's supposed to serve I decided to place
5 Access Points (AP's) in Building 4, 2 for floor 0 and 3 for floor 1. The placement of these devices isn't going to cause any issue
as they are not directly on top of each other and they are going to be programmed to different channels, this way they don't 
"intersect" each other.

# 3.0 Total Building Inventory


# Overall building inventory: #
|             Material             | How much is needed... (Floor 0) | How much is needed... (Floor 1) | How much is needed... (Total) | 
|:--------------------------------:|:-------------------------------:|:--------------------------------|:------------------------------|
|             Outlets              |               58                | 124                             | 182                           |
|     Main Cross-Connect (MCC)     |                0                | 0                               | 0                             | 
|  Horizontal Cross-Connect (HCC)  |                1                | 1                               | 2                             |
| Intermediate Cross-Connect (ICC) |                1                | 0                               | 1                             |  
|     Consolidation Point (CP)     |                3                | 6                               | 9                             | 
|        Access Point (AP)         |                2                | 3                               | 5                             |
|           Fiber cable            |               4 m               | 5.50 m                          | 9.50 m                        | 
|           Copper cable           |            721.32 m             | 1495.01 m                       | 2216.33 m                     |
|           Patch panels           |     5x 24 port patch panels     | 7x 24 port patch panels         | 12x 24 port patch panels      |                 
|             Switches             |           5x switches           | 7x switches                     | 12 switches -> 12U            |
|               Rack               |             1x 42U              | 1x 58U                          | 1x 42U and 1x 58U             |

