RCOMP 2024-2025 Project - Sprint 2 planning
===============================================
### Sprint master: 1230540 - Maria Francisca ###

`Note: This file include global technical decisions and details regarding team coordination.`

# Index
- [1. Sprint's backlog](#1-sprints-backlog-)
- [2. Tasks assignment to team members](#2-tasks-assignment-to-team-members-)
- [3. Some decisions](#3-some-decisions-)
  - [3.1 Version of Packet Tracer](#31-version-of-packet-tracer-being-used-)
  - [3.2 VLANIDs and VLAN names](#32-vlanids-and-vlan-names-)
  - [3.3 VTP domain name](#33-vtp-domain-name-)
  - [3.4 Block of IPv4 addresses](#34-block-of-ipv4-addresses-)
    - [3.4.1. Address Division](#341-address-division-)
    - [3.4.2 Address for each building](#342-address-for-each-building-)
  - [3.5 ISP router’s IPv4 node address](#35-isp-routers-ipv4-node-address-)
- [4. Sprint outputs](#4-sprint-outputs-)


# 1. Sprint's backlog #

| Task  | Description                                                                                                                                                                                                |
|-------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| T.2.1 | Development of a layer two and layer three Packet Tracer simulation for building 1, encompassing the campus backbone. <br>Integration of every member’s Packet Tracer simulation into a single simulation. |
| T.2.2 | Development of a layer two and layer three Packet Tracer simulation for building 2, encompassing the campus backbone.                                                                                      |
| T.2.3 | Development of a layer two and layer three Packet Tracer simulation for building 3, encompassing the campus backbone.                                                                                      |
| T.2.4 | Development of a layer two and layer three Packet Tracer simulation for building 4, encompassing the campus backbone.                                                                                      |

# 2. Tasks assignment to team members #

For this sprint, the tasks are the follow out of previous sprint’s tasks, thus they are already assigned. This means, a team member that was assigned in the previous sprint the structured cabling project of some building is now assigned the task of creating the Packet Tracer layer two and layer three simulation for that same building 

| Task  | Student number |
|-------|----------------|
| T.2.1 | 1230540        |
| T.2.2 | 1220849        |
| T.2.3 | 1230978        |
| T.2.4 | 1231500        |

# 3. Some decisions #

### 3.1. Version of Packet Tracer being used: ###

All group members must use the same version to avoid problems when joining simulations.

    The version we use: 8.2.2

---

### 3.2. VLANIDs and VLAN names: ###

VLANIDs and corresponding VLAN names to be used, they must be unique.

Based on the '_RCOMP - Sprint 2_' file, in point 7.1. '_VLANID and VTP domain names to be used by each team_':

| Class | Team number | VLANIDs range to be used |
|-------|-------------|--------------------------|
| 2DC   | 3           | 356 – 377                |

Based on the statement: "... for a project including the four buildings (teams with four members), there will be a total of: (4 x 5) + 1 = 21 VLANs ..."

| Student | Building | VLANIDs range to be used |
|---------|----------|--------------------------|
| 1230540 | 1        | 356 - 360                |
| 1220849 | 2        | 361 - 365                |
| 1230978 | 3        | 366 - 370                |
| 1231500 | 4        | 371 - 375                |
| 1230540 | Backbone | 377                      |

We decided to follow the following rule: 
 - `BX_Y` 
Where:
 - X : Building number;
 - Y : Ground_Floor / First_Floor / WiFi / DMZ / VoIP;

Note:
- the `Ground_Floor` vlan, refers to the vlan for all end-user outlets at the ground floor;
- the `First_Floor` vlan, refers to the vlan for all end-user outlets at floor one;
- the `WiFi` vlan, refers to the vlan for the Wi-Fi network (for all access-points’ outlets within the building);
- the `DMZ` vlan, refers to the vlan for the building DMZ (for servers, administration workstations, and infrastructure network devices);
- the `VoIP` vlan, refers to the vlan for VoIP (for all IP-phones within the building);

| VLANIDs | Name            |
|---------|-----------------|
| 1       | Default         |
| ...     | ...             |
| 356     | B1_Ground_Floor |
| 357     | B1_First_Floor  |
| 358     | B1_WiFi         |
| 359     | B1_DMZ          |
| 360     | B1_VoIP         |
| 361     | B2_Ground_Floor |
| 362     | B2_First_Floor  |
| 363     | B2_WiFi         |
| 364     | B2_DMZ          |
| 365     | B2_VoIP         |
| 366     | B3_Ground_Floor |
| 367     | B3_First_Floor  |
| 368     | B3_WiFi         |
| 369     | B3_DMZ          |
| 370     | B3_VoIP         |
| 371     | B4_Ground_Floor |
| 372     | B4_First_Floor  |
| 373     | B4_WiFi         |
| 374     | B4_DMZ          |
| 375     | B4_VoIP         |
| ...     | ...             |
| 377     | Backbone        |

It is necessary to decide which are the native VLAN (untagged) and which are the default VLAN.

    Default VLAN: 1 
    Native VLAN: 376
    Tagged VLAN: 356 - 375 and 377

---

### 3.3. VTP domain name: ###

You need to choose the VTP domain name to use. When all simulations are gathered on all switches belonging to each building they will maintain the same VLAN database talking to each other, so the VTP domain must be the same on all switches.

Based on the '_RCOMP - Sprint 2_' file, in point 7.1. '_VLANID and VTP domain names to be used by each team_':

| Class | Team number | VTP domain name to be used |
|-------|-------------|----------------------------|
| 2DC   | 3           | r2425dcg3                  |
 

---

### 3.4. Block of IPv4 addresses: ###

The IPv4 network address of the backbone network, this network address must belong to the address space provided to the team (addresses block), and yet it cannot overlap any of the blocks of addresses assigned to team members for use within each building.

All these blocks must belong to the address space provided to the team. They must be carefully settled, so that they are valid, non-overlapping, and they provide the required number of addresses for each building. Again, these blocks cannot overlap the backbone network address either.

Based on the '_RCOMP - Sprint 2_' file, in point 7.2. '_IPv4 addresses to be used, and the ISP router’s IPv4 address_':

| Class | Team number | IPv4 address space to be used <br> (Block of IPv4 addresses) |
|-------|-------------|--------------------------------------------------------------|
| 2DC   | 3           | 10.22.0.0/20                                                 |

---
### 3.4.1. Address Division: ###

IP Division Strategy:

In the process of dividing the IP addresses, we followed this approach: We decided to create a tree diagram to facilitate the visualization of the division.

- We began with a /20 subnet and split the IP range into two /21 subnets.
- Since a /21 subnet contains 2048 addresses, we needed to divide the addresses further to better match the number of nodes.
- We split each /21 subnet into two /22 subnets.
- We repeated this process, resulting in eight /23 subnets.
- Since a /23 subnet contains 512 addresses, it was still not suitable for our number of nodes.
- We then divided each /23 subnet into two /24 subnets.
- Each /24 subnet was further split into two /25 subnets.
- Given that the backbone network requires 220 nodes, a /24 subnet provides 256 addresses, and a /25 subnet provides 128 addresses. We allocated the backbone network to the first available subnet.
- We repeated the process down to the ground floor of Building 4, which has 125 nodes. Since this number no longer fits within a /24 subnet, we allocated it to the first available /25 subnet.
- As a /26 subnet contains 64 addresses, we placed the remaining VLANs, up to the VLAN for Building 1 VoIP (inclusive), into /25 subnets. By node count, this VLAN requires 70 addresses, and the next VLAN, with 60 nodes, will be assigned a /26 subnet.
- Given that a /27 subnet provides 32 addresses and all remaining VLANs have more than 32 nodes, we distributed the VLANs across the available /26 subnets.


![Address Division](/doc/sprint2/address_division.svg)


`Note: We use 10.22.14.0/26 for the router-to-router connections.`

---
### 3.4.2. Address for each building: ###

`Note: Table columns are in the order of the number of nodes.`

* Building 1:

| Nodes | Network prefix | End devices  | VLANID |      IP      |   First IP   |   Last IP    |  Broadcast   |
|:-----:|:--------------:|:------------:|:------:|:------------:|:------------:|:------------:|:------------:|
|  220  |       24       |   Backbone   |  377   |  10.22.2.0   |  10.22.0.1   | 10.22.2.254  | 10.22.2.255  |
|  110  |       25       |     DMZ      |  359   | 10.22.9.128  | 10.22.9.129  | 10.22.9.254  | 10.22.9.255  |
|  95   |       25       |     WiFi     |  358   |  10.22.11.0  |  10.22.11.1  | 10.22.11.126 | 10.22.11.127 | 
|  70   |       25       |     VoIP     |  360   | 10.22.11.128 | 10.22.11.129 | 10.22.11.254 | 10.22.11.255 |
|  45   |       26       | First Floor  |  357   | 10.22.12.192 | 10.22.12.193 | 10.22.12.254 | 10.22.12.255 |
|  40   |       26       | Ground Floor |  356   |  10.22.13.0  |  10.22.13.1  | 10.22.13.62  | 10.22.13.63  |

* Building 2:

| Nodes | Network prefix |     End devices      | VLANID |      IP      |   First IP   |    Last IP     |  Broadcast   |
|:-----:|:--------------:|:--------------------:|:------:|:------------:|:------------:|:--------------:|:------------:|
|  100  |       25       | Outlets Ground Floor |  361   | 10.22.10.128 | 10.22.10.129 |  10.22.10.254  | 10.22.10.255 |
|  110  |       25       | Outlets First Floor  |  362   |  10.22.10.0  |  10.22.10.1  |  10.22.10.126  | 10.22.10.127 |
|  200  |       24       |         Wifi         |  363   |  10.22.2.0   |  10.22.2.1   | 10.22.2.254  	 | 10.22.2.255  |
|  60   |       26       |         DMZ          |  364   |  10.22.12.0  |  10.22.12.1  |  10.22.12.62   | 10.22.12.63  |
|  120  |       25       |         VoIP         |  365   | 10.22.8.128  | 10.22.8.129  |  10.22.8.254   | 10.22.8.255  |

* Building 3:

| Nodes | Network prefix | End devices  | VLANID |     IP      |  First IP   |   Last IP    |  Broadcast   |
|:-----:|:--------------:|:------------:|:------:|:-----------:|:-----------:|:------------:|:------------:|
|  220  |       24       |     WiFi     |  368   |  10.22.1.0  |  10.22.1.1  | 10.22.1.254  | 10.22.1.255  |
|  170  |       24       |     VoIP     |  370   |  10.22.4.0  |  10.22.4.1  | 10.22.4.254  | 10.22.4.255  |
|  135  |       24       | First Floor  |  367   |  10.22.7.0  |  10.22.7.1  | 10.22.7.254  | 10.22.7.255  |
|  115  |       25       | Ground Floor |  366   |  10.22.9.0  |  10.22.9.1  | 10.22.9.126  | 10.22.9.127  |
|  50   |       26       |     DMZ      |  369   | 10.22.12.64 | 10.22.12.65 | 10.22.12.126 | 10.22.12.127 |

* Building 4:

| Nodes | Network prefix | End devices  | VLANID |      IP      |   First IP   |   Last IP    |   Broadcast   |
|:-----:|:--------------:|:------------:|:------:|:------------:|:------------:|:------------:|:-------------:|
|  200  |       24       |     WiFi     |  373   |  10.22.3.0   |  10.22.3.1   | 10.22.3.254  |  10.22.3.255  |
|  170  |       24       | First Floor  |  372   |  10.22.5.0   |  10.22.5.1   | 10.22.5.254  |  10.22.5.255  | 
|  160  |       24       |     VoIP     |  375   |  10.22.6.0   |  10.22.6.1   | 10.22.6.254  |  10.22.6.255  |
|  110  |       25       | Ground Floor |  371   |  10.22.8.0   |  10.22.8.1   | 10.22.8.126  | 10.22.12.127  |
|  45   |       26       |     DMZ      |  374   | 10.22.12.128 | 10.22.12.129 | 10.22.13.190 | 10.22.12.191  |

---

### 3.5. ISP router’s IPv4 node address: ###

| Class | Team number | ISP router’s IPv4 node address |
|-------|-------------|--------------------------------|
| 2DC   | 3           | 87.5.127.69/30                 |


# 4. Sprint outputs #

* For each task in the sprint, the main output is the Packet Tracer simulation file for the corresponding building, named buildingN.pkt, where N represents the building number.
---
* Each team member must commit their file into their personal sprint folder in the repository.
* Additionally, a document detailing the establishment of IPv4 network addresses and the creation of static routing tables is required.
---
* Each team member is also required to commit a text file containing the configuration dump for every switch and router in the building.

`Note: These configuration files can be exported directly from Packet Tracer by clicking the export button on the Running Config or Startup Config.`

---
* Team members should commit any changes made to these files during the sprint to allow the teacher to track progress.
* These files also serve as backups, enabling restoration of configurations in case they are lost.
---
* For task T.2.1, an additional output is required: an integrated simulation file named campus.pkt, which includes the work of all team members.


