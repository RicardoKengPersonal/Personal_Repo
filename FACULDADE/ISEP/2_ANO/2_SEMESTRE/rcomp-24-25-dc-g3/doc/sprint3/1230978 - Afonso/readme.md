# RCOMP 2024-2025 Project - Sprint 3
## Afonso Sousa - 1230978

---

## 1. OSPF Dynamic Routing

> **Nota:** Os identificadores de área OSPF devem ser distintos entre edifícios, mas não é necessário que se conheçam entre si.

- **Área OSPF do Edifício:** ` id = 3` (correspondente ao Edifício 3)

---

## 2. HTTP Server

> O serviço HTTP deve estar ativo e servir uma página HTML identificando o edifício.

- **Servidor HTTP:** `Server_HTTP_3`
- **Ficheiro HTML:** [`index.html`](index.html)

---

## 3. DHCPv4 Service

|                | Floor 0 Network | Floor 1 Network | WiFi Network | VoIP Network | DMZ Network |
|----------------|-----------------|-----------------|--------------|--------------|-------------|
| **Building 3** | FLOOR0_B3       | FLOOR1_B3       | WIFI_B3      | VOIP_B3      | DMZ_B3      |

- **Domain Name:** `building3.rcomp-24-25-dc-g3`
- **DNS Server IPv4:** `10.22.12.67`
- **VTP Domain:** `r2425dcg3`

### DHCP Pools (B3_Router)

    ip dhcp pool FLOOR0_B3
    network 10.22.9.0 255.255.255.128
    default-router 10.22.9.126
    dns-server 10.22.12.67
    domain-name r2425dcg3
    ip dhcp pool FLOOR1_B3
    network 10.22.7.0 255.255.255.0
    default-router 10.22.7.254
    dns-server 10.22.12.67
    domain-name r2425dcg3
    ip dhcp pool WIFI_B3
    network 10.22.1.0 255.255.255.0
    default-router 10.22.1.254
    dns-server 10.22.12.67
    domain-name r2425dcg3
    ip dhcp pool VOIP_B3
    network 10.22.4.0 255.255.255.0
    default-router 10.22.4.254
    option 150 ip 10.22.4.254
    dns-server 10.22.12.67
    domain-name r2425dcg3
   

# 4. VoIP service

|            | F0 Phone Number | F1 Phone Number |
|:----------:|:---------------:|:---------------:|
| Building 3 |      3001       |      3002       |
    
# 5. DNS
    
|            |          Local DNS          | DNS Server IPv4 |
|:----------:|:---------------------------:|:---------------:|
| Building 3 | building3.rcomp-24-25-dc-g3 |  (10.22.12.67)  |
    
![DNS RouterB3](Server_DNS_B3.png)
    
# 6. NAT


    ! NAT para HTTP (80) e HTTPS (443)
    ip nat inside source static tcp 10.22.12.66 80 10.22.40.30 80
    ip nat inside source static tcp 10.22.12.66 443 10.22.40.30 443

    ! NAT para DNS (TCP e UDP na porta 53)
    ip nat inside source static tcp 10.22.12.67 53 10.22.40.30 53
    ip nat inside source static udp 10.22.12.67 53 10.22.40.30 53


10.22.12.66 --> ip do Server_HTTP (gateaway : 10.22.12.65)
    
10.22.12.67 --> ip do Server_DNS

    
# 7. Static Firewall (ACLs) [Didnt make it in time]
    
***Disclaimer: these configs were exported from packet tracer***
    
Configurar ACL contra Internal Spoofing:
    
    **"Outlets floor 0"**
    
        
    
    **"Outlets floor 1"**
    
       
    
    **"Wi-Fi network"**
    
      
    **"VoIP"**
    
       
    
    Configurar ACL contra External Spoofing:
    
       
        
