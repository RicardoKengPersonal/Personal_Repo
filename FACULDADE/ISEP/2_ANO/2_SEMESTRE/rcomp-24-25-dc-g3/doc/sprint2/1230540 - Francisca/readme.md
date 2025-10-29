RCOMP 2023-2024 Project - Sprint 2 - Building 1
===========================================
### Student: 1230540 - Maria Francisca ###

## IPv4 networks ##

- End user outlets on the ground floor: `40 nodes`
- End user outlets on floor one: `45 nodes`
- Wi-Fi network: `95 nodes`
- DMZ (Servers, administration workstations, and network infrastructure devices): `110 nodes`
- VoIP (IP-phones): `70 nodes`
- Backbone: `220 nodes`

- VTP Domain name: `r2324dcg3`


## VLAN DETAILS ##

| Building | VLAN ID |    VLAN Name    |
|:--------:|:-------:|:---------------:|
|    1     |   356   | B1_Ground_Floor |
|    1     |   357   | B1_First_Floor  |
|    1     |   358   |     B1_WiFi     |
|    1     |   359   |     B1_DMZ      |
|    1     |   360   |     B1_VoIP     |
|    1     |   377   |    Backbone     |

## IPV4 DETAILS ##

| Nodes | Network prefix | End devices  | VLANID |      IP      |   First IP   |   Last IP    |  Broadcast   |
|:-----:|:--------------:|:------------:|:------:|:------------:|:------------:|:------------:|:------------:|
|  220  |       24       |   Backbone   |  377   |  10.22.2.0   |  10.22.0.1   | 10.22.2.254  | 10.22.2.255  |
|  110  |       25       |     DMZ      |  359   | 10.22.9.128  | 10.22.9.129  | 10.22.9.254  | 10.22.9.255  |
|  95   |       25       |     WiFi     |  358   |  10.22.11.0  |  10.22.11.1  | 10.22.11.126 | 10.22.11.127 | 
|  70   |       25       |     VoIP     |  360   | 10.22.11.128 | 10.22.11.129 | 10.22.11.254 | 10.22.11.255 |
|  45   |       26       | First Floor  |  357   | 10.22.12.192 | 10.22.12.193 | 10.22.12.254 | 10.22.12.255 |
|  40   |       26       | Ground Floor |  356   |  10.22.13.0  |  10.22.13.1  | 10.22.13.62  | 10.22.13.63  |