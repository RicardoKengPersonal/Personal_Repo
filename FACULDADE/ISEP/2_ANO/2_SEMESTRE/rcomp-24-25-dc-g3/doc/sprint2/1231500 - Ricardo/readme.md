RCOMP 2023-2024 Project - Sprint 2 - Building 4
===========================================
### Student: 1231500 - Ricardo Keng Machado da Silva ###

## IPv4 networks ##

- End user outlets on the ground floor: `125 nodes`
- End user outlets on floor one: `170 nodes`
- Wi-Fi network: `200 nodes`
- DMZ (Servers, administration workstations, and network infrastructure devices): `110 nodes`
- VoIP (IP-phones): `45 nodes`
- Backbone: `160 nodes`

- VTP Domain name: `r2324dcg3`


## VLAN DETAILS ##

| Building | VLAN ID |    VLAN Name    |
|:--------:|:-------:|:---------------:|
|    4     |   371   | B4_Ground_Floor |
|    4     |   372   | B4_First_Floor  |
|    4     |   373   |     B4_WiFi     |
|    4     |   374   |     B4_DMZ      |
|    4     |   375   |     B4_VoIP     |
|    1     |   377   |    Backbone     |

## IPV4 DETAILS ##

| Nodes | Network prefix | End devices  | VLANID |      IP      |   First IP   |   Last IP    |   Broadcast   |
|:-----:|:--------------:|:------------:|:------:|:------------:|:------------:|:------------:|:-------------:|
|  220  |       24       |   Backbone   |  377   |  10.22.2.0   |  10.22.0.1   | 10.22.2.254  |  10.22.2.255  |
|  200  |       24       |     WiFi     |  373   |  10.22.3.0   |  10.22.3.1   | 10.22.3.254  |  10.22.3.255  |
|  170  |       24       | First Floor  |  372   |  10.22.5.0   |  10.22.5.1   | 10.22.5.254  |  10.22.5.255  | 
|  160  |       24       |     VoIP     |  375   |  10.22.6.0   |  10.22.6.1   | 10.22.6.254  |  10.22.6.255  |
|  110  |       25       | Ground Floor |  371   |  10.22.8.0   |  10.22.8.1   | 10.22.8.126  | 10.22.12.127  |
|  45   |       26       |     DMZ      |  374   | 10.22.12.128 | 10.22.12.129 | 10.22.13.190 | 10.22.12.191  |


## End user devices details:

PC01_B4F0 -> Ip address: 10.22.8.2 / Mask: 255.255.255.128 / Default Gateway: 10.22.8.1
PC02_B4F0 -> Ip address: 10.22.8.3 / Mask: 255.255.255.128 / Default Gateway: 10.22.8.1
PC03_B4F0 -> Ip address: 10.22.8.4 / Mask: 255.255.255.128 / Default Gateway: 10.22.8.1
PC04_B4F0 -> Ip address: 10.22.8.5 / Mask: 255.255.255.128 / Default Gateway: 10.22.8.1
PC05_B4F0 -> Ip address: 10.22.8.6 / Mask: 255.255.255.128 / Default Gateway: 10.22.8.1
PC06_B4F0 -> Ip address: 10.22.8.7 / Mask: 255.255.255.128 / Default Gateway: 10.22.8.1
PC07_B4F0 -> Ip address: 10.22.8.8 / Mask: 255.255.255.128 / Default Gateway: 10.22.8.1
PC08_B4F0 -> Ip address: 10.22.8.9 / Mask: 255.255.255.128 / Default Gateway: 10.22.8.1
PC09_B4F0 -> Ip address: 10.22.8.10 / Mask: 255.255.255.128 / Default Gateway: 10.22.8.1

Server_B4F0 -> Ip address: 10.22.12.130 / Mask: 255.255.255.192 / Default Gateway: 10.22.12.129

LP01_B4F0 -> Ip address: 10.22.3.4 / Mask: 255.255.255.0 / Default Gateway: 10.22.3.1
LP02_B4F0 -> Ip address: 10.22.3.2 / Mask: 255.255.255.0 / Default Gateway: 10.22.3.1
Smartphone1 -> Ip address: 10.22.3.10 / Mask: 255.255.255.0 / Default Gateway: 10.22.3.1
Tablet1 -> Ip address: 10.22.3.11 / Mask: 255.255.255.0 / Default Gateway: 10.22.3.1

# Floor 1

PC10_B4F0 -> Ip address: 10.22.5.2 / Mask: 255.255.255.0 / Default Gateway: 10.22.5.1
PC11_B4F0 -> Ip address: 10.22.5.3 / Mask: 255.255.255.0 / Default Gateway: 10.22.5.1
PC12_B4F0 -> Ip address: 10.22.5.4 / Mask: 255.255.255.0 / Default Gateway: 10.22.5.1
PC13_B4F0 -> Ip address: 10.22.5.5 / Mask: 255.255.255.0 / Default Gateway: 10.22.5.1
PC14_B4F0 -> Ip address: 10.22.5.6 / Mask: 255.255.255.0 / Default Gateway: 10.22.5.1
PC15_B4F0 -> Ip address: 10.22.5.7 / Mask: 255.255.255.0 / Default Gateway: 10.22.5.1
PC16_B4F0 -> Ip address: 10.22.5.8 / Mask: 255.255.255.0 / Default Gateway: 10.22.5.1
PC17_B4F0 -> Ip address: 10.22.5.9 / Mask: 255.255.255.0 / Default Gateway: 10.22.5.1
PC18_B4F0 -> Ip address: 10.22.5.10 / Mask: 255.255.255.0 / Default Gateway: 10.22.5.1
PC19_B4F0 -> Ip address: 10.22.5.11 / Mask: 255.255.255.0 / Default Gateway: 10.22.5.1
PC20_B4F0 -> Ip address: 10.22.5.12 / Mask: 255.255.255.0 / Default Gateway: 10.22.5.1
PC21_B4F0 -> Ip address: 10.22.5.13 / Mask: 255.255.255.0 / Default Gateway: 10.22.5.1
PC22_B4F0 -> Ip address: 10.22.5.14 / Mask: 255.255.255.0 / Default Gateway: 10.22.5.1
PC23_B4F0 -> Ip address: 10.22.5.15 / Mask: 255.255.255.0 / Default Gateway: 10.22.5.1
PC24_B4F0 -> Ip address: 10.22.5.16 / Mask: 255.255.255.0 / Default Gateway: 1o.22.5.1
PC25_B4F0 -> Ip address: 10.22.5.17 / Mask: 255.255.255.0 / Default Gateway: 10.22.5.1

LP03_B4F1 -> Ip address: 10.22.3.3 / Mask: 255.255.255.0 / Default Gateway: 10.22.3.1
LP04_B4F1 -> Ip address: 10.22.3.7 / Mask: 255.255.255.0 / Default Gateway: 10.22.3.1
LP05_B4F1 -> Ip address: 10.22.3.5 / Mask: 255.255.255.0 / Default Gateway: 10.22.3.1
LP06_B4F1 -> Ip address: 10.22.3.6 / Mask: 255.255.255.0 / Default Gateway: 10.22.3.1
LP07_B4F1 -> Ip address: 10.22.3.9 / Mask: 255.255.255.0 / Default Gateway: 10.22.3.1

Smartphone2 -> Ip address: 10.22.3.12 / Mask: 255.255.255.0 / Default Gateway: 10.22.3.1 
