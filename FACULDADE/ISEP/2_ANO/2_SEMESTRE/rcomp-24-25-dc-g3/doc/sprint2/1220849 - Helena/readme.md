# Rcomp: Sprint2: Bulding 2
## Maria Pinho- 1220849


- End user outlets on the ground floor: 100 nodes
- End user outlets on floor one: 110 nodes
- Wi-Fi network: 200 nodes
- DMZ (Servers, administration workstations, and network infrastructure devices): 60 nodes
- VoIP (IP-phones): 120 nodes


- VTP Domain name: **r2425dcg3**


## VLAN DETAILS ##

| Building | VLAN ID |    VLAN Name    |
|:--------:|:-------:|:---------------:|
|    2     |   361   | B2_Ground_Floor |
|    2     |   362   | B2_First_Floor  |
|    2     |   363   |     B2_WiFi     |
|    2     |   364   |     B2_DMZ      |
|    2     |   365   |     B2_VoIP     |

## IPV4  ##


| Nodes | Network prefix |     End devices      | VLANID |      IP      |   First IP   |    Last IP     |  Broadcast   |
|:-----:|:--------------:|:--------------------:|:------:|:------------:|:------------:|:--------------:|:------------:|
|  100  |       25       | Outlets Ground Floor |  361   | 10.22.10.128 | 10.22.10.129 |  10.22.10.254  | 10.22.10.255 |
|  110  |       25       | Outlets First Floor  |  362   |  10.22.10.0  |  10.22.10.1  |  10.22.10.126  | 10.22.10.127 |
|  200  |       24       |         Wifi         |  363   |  10.22.2.0   |  10.22.2.1   | 10.22.2.254  	 | 10.22.2.255  |
|  60   |       26       |         DMZ          |  364   |  10.22.12.0  |  10.22.12.1  |  10.22.12.62   | 10.22.12.63  |
|  120  |       25       |         VoIP         |  365   | 10.22.8.128  | 10.22.8.129  |  10.22.8.254   | 10.22.8.255  |
