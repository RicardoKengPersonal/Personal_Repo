RCOMP 2024-2025 Project - Sprint 3 planning
===============================================
### Sprint master: 1220849 - Maria Helena Pinho ###

`Note: This file include global technical decisions and details regarding team coordination.`

# Index
- [1. Sprint's backlog](#1-sprints-backlog-)
- [2. Tasks assignment to team members](#2-tasks-assignment-to-team-members-)
- [3. Some information](#3-some-information-)
  - [3.1 OSPF dynamic routing](#31-ospf-dynamic-routing)
  - [3.2 HTTP servers](#32--http-servers)
  - [3.3 DHCPv4 service](#33-dhcpv4-service)
  - [3.4 VoIP service](#34-voip-service)
  - [3.5 DNS](#35-dns)
    - [3.5.1 NS (Name Server) records and glue records](#351-ns-name-server-records-and-glue-records)
    - [3.5.2 Other DNS records](#352-other-dns-records)
    - [3.5.3 DNS clients’ configuration (end-nodes)](#353-dns-clients-configuration-end-nodes)
  - [3.6 NAT (Network Address Translation)](#36-nat-network-address-translation)
  - [3.7 Static Firewall (ACLs)](#37-static-firewall-acls)
  - [3.8 Teamwork organization](#38-teamwork-organization)
- [4. Some decisions](#4-some-decisions-)
  - [4.1 Sprint Master](#41-sprint-master) 
  - [4.2 OSPF Area IDs](#42-ospf-area-ids)
  - [4.3 VoIP Numbering](#43-voip-numbering)
  - [4.4 DNS Domain Names](#44-dns-domain-names)
  - [4.5 DNS Server IPs](#45--dns-server-ips)
- [5. Sprint Outputs](#5-sprint-outputs-)


# 1. Sprint's backlog #

| Task  | Description                                                                                                                                                                                                                                                        |
|-------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| T.3.1 | Update the `building1.pkt` layer three Packet Tracer simulation from the previous sprint, to include the described features in this sprint for `building one`.                                                                                                         |
| T.3.2 | Update the `building2.pkt` layer three Packet Tracer simulation from the previous sprint, to include the described features in this sprint for `building two`. <br> Final integration of each member’s Packet Tracer simulation into a single simulation (`campus.pkt`). |
| T.3.3 | Update the `building3.pkt` layer three Packet Tracer simulation from the previous sprint, to include the described features in this sprint for `building three`.                                                                                                       |
| T.3.4 | Update the `building4.pkt` layer three Packet Tracer simulation from the previous sprint, to include the described features in this sprint for `building four`.                                                                                                        |

# 2. Tasks assignment to team members #

For this sprint, the tasks are the follow out of previous sprint’s tasks, thus they are already assigned. This means, a team member that was assigned in the previous sprint the structured cabling project of some building is now assigned the task of creating the Packet Tracer layer two and layer three simulation for that same building

| Task  | Student number |
|-------|----------------|
| T.3.1 | 1230540        |
| T.3.2 | 1220849        |
| T.3.3 | 1230978        |
| T.3.4 | 1231500        |


# 3. Some information #

In this sprint, each team member will keep working on the same network simulation from the previous sprint (concerning the same building). From the already established layer three configurations, now, OSPF based dynamic routing will be used to replace the previously used static routing.

Beyond OSPF based dynamic routing, other configurations included in this sprint are:
- DHCPv4 service
- VoIP service
- Adding a second server to each DMZ network to run the HTTP service.
- Configuring DNS servers to establish a DNS domains tree.
- Enforcing NAT (Network Address Translation).
- Establishing traffic access policies on routers (static firewall).


## 3.1 OSPF dynamic routing

- Static Routing Removal:
    - Static routing will be removed from all routers, except for the default route on the Building 1 router.

- Default Route Exception:
    - The default route pointing to the ISP will remain only on the Building 1 router.

- OSPF Adoption:
    - The entire network will transition to OSPF (Open Shortest Path First) as the dynamic routing protocol.

- OSPF Areas Structure:
    - The OSPF domain will be split into multiple areas.

- Each building will have a unique OSPF area ID.
    - All IPv4 networks in a building will belong to its specific area.

- Backbone Area (Area 0):
    - Area ID 0 will act as the backbone.
    - It will include only the backbone VLAN IPv4 network.

- Area ID Assignment:
    - Unique area IDs for each building were decided during the planning meeting.

- Default Route Advertisement:
    - The Building 1 team is responsible for injecting the default route into OSPF using the default-information originate command.

- OSPF ID:

| Building | AREA ID |
|----------|---------|
| Backbone | 0       |
| 1        | 1       |
| 2        | 2       |
| 3        | 3       |
| 3        | 5       |


## 3.2  HTTP servers

- New Server Deployment:
  - In addition to the existing server, each building must deploy a new server within its local DMZ network.

- Manual IP Configuration:
    - The new server must be configured with a static (manually set) IPv4 address.

- Web Services Activation:
    - TTP and HTTPS services must be enabled on the new server.

- Web Page Requirement:
    - A simple HTML page must be created and hosted, clearly identifying the building it belongs to.

## 3.3 DHCPv4 service

- DHCP Server Location:
  - Each building's router will act as the DHCPv4 server.

- Scope of Service:
  - DHCP will be provided to all IPv4 networks within the building, excluding:
    - DMZ networks
    - The backbone network (These use static/manual IP addressing)

- VoIP VLAN Configuration:
  - DHCP configuration must include Option 150.
  - This option specifies the IPv4 address of the TFTP server.
  - It is required by Cisco IP Phone 7960 to download its configuration file.


## 3.4 VoIP service

- VoIP Provision:
  - Each building's router provides Cisco Telephony (VoIP) for the local VoIP network.

- Inter-Building Call Forwarding:
  - Calls to prefixes assigned to other buildings must be forwarded to the respective building’s VoIP router.

- IP Phones for Testing:
  - At least two Cisco IP Phone 7960 devices must be connected in each building for local VoIP testing.

- Switch Port Configuration:
  - Enable the voice VLAN (switchport voice vlan VLANID).
  - Disable the access VLAN (no switchport access vlan).


## 3.5 DNS

- Domain Structure:
  - A DNS domain will be established in each building.
  - Building 1 will create the top-level domain: `rcomp-24-25-dc-g3`.
  - Other buildings will create subdomains: `building-X.rcomp-24-25-dc-g3`, replacing X with the building number.

- DNS Servers Naming:
  - All DNS servers will use the unqualified name ns, resulting in FQDNs like:
    - `ns.rcomp-24-25-dc-g3` (Building 1)
    - `ns.building-3.rcomp-24-25-dc-g3` (Building 2)
    - `ns.building-3.rcomp-24-25-dc-g3` (Building 3)
    - `ns.building-3.rcomp-24-25-dc-g3` (Building 4)

- DNS Server Location:
  - The DNS server in each building will be hosted on the server already deployed in the DMZ.

- DNS Interconnection:
  - The DNS server in Building 1 must know the IPv4 addresses of all subdomain DNS servers (Buildings 2, 3, 4).
  - All other DNS servers must know the IPv4 address of the Building 1 DNS server (root domain server).

- FQDN Requirement:
  - All DNS entries must use fully qualified domain names ending in rcomp-24-25-dc-g3.
  - Optional: a final dot (.) may be added (e.g., host.building-2.rcomp-24-25-dc-g3.), but it must be used consistently across all records.

## 3.5.1 NS (Name Server) records and glue records

- NS Records:
  - Required for each domain to reference its subdomains and the root domain.
  - Format: the domain name → the DNS name of the name server.

- Glue Records (A Records):
  - Required to resolve the DNS name of the name server to its IPv4 address.
  - Format: the name server's FQDN → IPv4 address.
  - Only IPv4 is used in this setup (no AAAA records).

## 3.5.2 Other DNS records

- HTTP Server Naming:
  - Each HTTP server must have an A record named server1.
  - Repeated names are acceptable due to separate domains.

- CNAME Aliases per Domain:
  - `www` → alias to `server1`
  - `web` → alias to `server1`
  - `dns` → alias to `ns` (name server of the domain)

## 3.5.3 DNS clients’ configuration (end-nodes)

- DNS Server Usage:
  - All end-nodes must use the local building’s DNS name server.

- Default Domain Name:
  - If supported, end-nodes should be configured with the local DNS domain name as default.

- DHCP-configured End-nodes:
  - Add DNS-related settings in the local DHCP pool using:
    - dns-server (IP of local DNS server)
    - domain-name (local DNS domain)

- Statically-configured End-nodes (e.g., servers):
  - DNS settings must be manually configured.

## 3.6 NAT (Network Address Translation)

- Purpose:
  - Static NAT is used in this sprint to redirect traffic, not to hide private IPs.

- Required NAT Configuration (per building):
  - HTTP (port 80) and HTTPS (port 443) requests received on the router’s backbone interface must be redirected to the local DNS server in the building’s DMZ.

- DNS Server Setup:
  - The DNS server must have HTTP and HTTPS services enabled.
  - It should serve a simple HTML page identifying the building.

## 3.7 Static Firewall (ACLs)

Static firewalls use Access Control Lists (ACLs) to define which packets are allowed or blocked on router interfaces. They are stateless, meaning they do not track connection state, and behave the same way for every packet.

- ACLs must be configured only after all other features are tested and working.
  - ACLs are applied on each building’s router, especially:
    - Traffic to/from the DMZ
    - Traffic to the router itself
      
Traffic Access Policies – in Priority Order:

| Priority |      Policy       |                                                                                                                   Action                                                                                                                    |
|:--------:|:-----------------:|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
|    1     |   Anti-spoofing   |                                                                 Block spoofed packets: <br> • From internal networks (except DMZ) <br> • From backbone (external spoofing)                                                                  |
|    2     |       ICMP        |                                                                                                  Allow all ICMP echo request/reply (ping)                                                                                                   |
|    3     |    DMZ Access     |                                          Block all incoming traffic to DMZ, except: <br> • DNS (UDP port 53) <br> • HTTP (TCP 80) <br> • HTTPS (TCP 443) <br> Allow all incoming traffic from DMZ                                           |
|    4     | Router Protection | Block all traffic destined to router interfaces, except for: <br> - DHCPv4 (discover from 0.0.0.0 to 255.255.255.255) <br> - TFTP (for IP phones) <br> - ITS service (phones and IST servers) <br> - OSPF routing <br> - Static NAT traffic |
|    5     |  Transit Traffic  |                                                                                       Allow all other through traffic (router not final destination)                                                                                        |

## 3.8 Teamwork organization

All members:
- Start from sprint 2 simulation (campus.pkt)
- Continue managing their assigned building
- Replace static routing with OSPF in all routers (including other buildings), but no other changes outside their building

Member in charge of Building 2:
- Merge all individual work into a single final simulation (campus.pkt)
- Use copy-paste in Packet Tracer
- Best cut point: building’s main switch to backbone
- Ensure trunk-mode on all switch connections

# 4. Some decisions #

# 4.1 Sprint Master

    As in Sprint 1, Afonso was the Sprint Master, and in the previous sprint, it was Maria Francisca; as a team, we have decided that for this sprint, Maria Helena will take on the role of Sprint Master.

# 4.2 OSPF Area IDs

    Each building must use a unique area ID (Backbone = area 0)


# 4.3 VoIP Numbering



# 4.4 DNS Domain Names

    Follow this structure:
    • Root domain: rcomp-24-25-cc-gn
    • Subdomains: building-X.rcomp-24-25-cc-gn

# 4.5  DNS Server IPs



# 5. Sprint outputs #

- Per Building:

Each team member must produce and commit the following into their personal sprint folder:
- `buildingN.pkt` : Packet Tracer simulation file for the assigned building
- `DNS Records Document` : A file showing DNS database records for the local DNS server
- `Router & Switch Configs` : Exported configuration files (running config) of all routers and switches in the building

- Integration (Task T.3.2.):
  Only the member responsible for Building 2 must additionally:
- `campus.pkt` : The final integrated simulation file combining all buildings
- `Config files` : Only required for devices in Building 2

