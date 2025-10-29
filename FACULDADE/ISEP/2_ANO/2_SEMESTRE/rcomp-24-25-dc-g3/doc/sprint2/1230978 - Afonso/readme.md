RCOMP 2024-2025 Project - Sprint 2 - Building 3
===========================================
### Student: 1230978 - Afonso Sousa ###

## IPv4 networks ##

- End user outlets on the ground floor: `115 nodes`
- End user outlets on floor one: `135 nodes`
- Wi-Fi network: `220 nodes`
- DMZ (Servers, administration workstations, and network infrastructure devices): `50 nodes`
- VoIP (IP-phones): `170 nodes`


- VTP Domain name: `r2425dcg3`


## VLAN DETAILS ##

| Building | VLAN ID |   VLAN Name    |
|:--------:|:-------:|:--------------:|
|    3     |   366   | B3_Floor0_VLAN |
|    3     |   367   | B3_Floor1_VLAN |
|    3     |   368   |  B3_WIFI_VLAN  |
|    3     |   369   |  B3_DMZ_VLAN   |
|    3     |   370   |  B3_voIP_VLAN  |

## IPV4 DETAILS ##

| Nodes | Network prefix | End devices  | VLANID |     IP      |  First IP   |   Last IP    |  Broadcast   |
|:-----:|:--------------:|:------------:|:------:|:-----------:|:-----------:|:------------:|:------------:|
|  220  |       24       |     WiFi     |  368   |  10.22.1.0  |  10.22.1.1  | 10.22.1.254  | 10.22.1.255  |
|  170  |       24       |     VoIP     |  370   |  10.22.4.0  |  10.22.4.1  | 10.22.4.254  | 10.22.4.255  |
|  135  |       24       | First Floor  |  367   |  10.22.7.0  |  10.22.7.1  | 10.22.7.254  | 10.22.7.255  |
|  115  |       25       | Ground Floor |  366   |  10.22.9.0  |  10.22.9.1  | 10.22.9.126  | 10.22.9.127  |
|  50   |       26       |     DMZ      |  369   | 10.22.12.64 | 10.22.12.65 | 10.22.12.126 | 10.22.12.127 |


