RCOMP 2024-2025 Project - Sprint 2 Report
===============================================
### Sprint master: 1230540 - Maria Francisca ###

The goal in this second sprint is creating a network simulation for the structured cabling project developed in the first sprint. In this sprint the focus is on the layer two infrastructure, and the layer three fundamentals (IPv4 addressing, and static routing).

For such purposes, the `Cisco Packet Tracer` is the tool that will be used.

### VLAN ###

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

### VTP domain name ### 

- r2425dcg3

### Number of nodes ###

| Building | Ground floor end-user outlets | First floor end-user outlets | Wi-Fi network | DMZ <br> (Servers, administration workstations, and network infrastructure devices) | VoIP | Backbone |
|----------|-------------------------------|------------------------------|---------------|-------------------------------------------------------------------------------------|------|----------|
| 1        | 40                            | 45                           | 95            | 110                                                                                 | 70   | 220      |
| 2        | 100                           | 110                          | 200           | 60                                                                                  | 120  | -        |
| 3        | 115                           | 135                          | 220           | 50                                                                                  | 170  | -        |
| 4        | 125                           | 170                          | 200           | 45                                                                                  | 160  | -        |

### IPv4 addresses ###

- 10.22.0.0/20

Our division:

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
|  200  |       24       |         Wifi         |  363   |  10.22.2.0   |  10.22.2.1   | 10.22.2.254  	 | 10.22.2.255  |
|  120  |       25       |         VoIP         |  365   | 10.22.8.128  | 10.22.8.129  |  10.22.8.254   | 10.22.8.255  |
|  110  |       25       | Outlets First Floor  |  362   |  10.22.10.0  |  10.22.10.1  |  10.22.10.126  | 10.22.10.127 |
|  100  |       25       | Outlets Ground Floor |  361   | 10.22.10.128 | 10.22.10.129 |  10.22.10.254  | 10.22.10.255 |
|  60   |       26       |         DMZ          |  364   |  10.22.12.0  |  10.22.12.1  |  10.22.12.62   | 10.22.12.63  |


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


### ISP router’s IPv4 node address ###

- 87.5.127.69/30

### Number of addresses ###

    As X increases, the number of addresses decreases.

| /X  | Number of addresses |
|:---:|:-------------------:|
| 20  |        4096         |
| 21  |        2048         |
| 22  |        1024         |
| 23  |         512         |
| 24  |         256         |
| 25  |         128         |
| 26  |         64          |
| 27  |         32          |
| ... |         ...         |
| 30  |          4          |

### Mask ###

| /X  |      Mask       |
|:---:|:---------------:|
| 20  |  255.255.240.0  |
| ... |       ...       |
| 24  |  255.255.255.0  |
| 25  | 255.255.255.128 |
| 26  | 255.255.255.192 |
| ... |       ...       |
| 30  | 255.255.255.252 |

