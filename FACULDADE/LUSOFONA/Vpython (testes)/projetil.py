from vpython import *

# Cria a cena
scene = canvas(title='Lançamento de Projétil VPython', width=800, height=600)

# Define as constantes
g = vector(0, -9.8, 0)  # Aceleração da gravidade
v0 = 20  # Velocidade inicial
angle = radians(45)  # Ângulo de lançamento

# Cria o objeto projetil
proj = sphere(pos=vector(0, 0, 0), radius=0.2, color=color.red)

# Define a velocidade inicial do projétil
v0x = v0 * cos(angle)
v0y = v0 * sin(angle)
v = vector(v0x, v0y, 0)

# Define o tempo inicial
t = 0

# Loop de animação
while proj.pos.y >= 0:
    rate(60)  # Limita a taxa de atualização da animação
    
    # Atualiza a posição do projétil
    proj.pos += v * 0.01
    
    # Atualiza a velocidade do projétil considerando a aceleração da gravidade
    v += g * 0.01
    
    # Atualiza o tempo
    t += 0.01
