from vpython import *

# Cria a cena
scene = canvas(title='Pêndulo VPython', width=800, height=600)

# Cria o teto
ceiling = box(pos=vector(0, 5, 0), size=vector(0.5, 0.1, 0.5), color=color.blue)

# Cria a corda
rod = cylinder(pos=vector(0, 5, 0), axis=vector(0, -4, 0), radius=0.05, color=color.white)

# Cria a esfera
ball = sphere(pos=rod.axis, radius=0.2, color=color.red)

# Define as propriedades do pêndulo
length = 4
angle = 0.2
angular_velocity = 0
gravity = 9.8

# Loop de animação
while True:
    rate(60)  # Limita a taxa de atualização da animação
    
    # Calcula a aceleração angular
    angular_acceleration = -gravity / length * sin(angle)
    
    # Atualiza a velocidade angular
    angular_velocity += angular_acceleration * 0.01
    
    # Atualiza o ângulo
    angle += angular_velocity * 0.01
    
    # Atualiza a posição da esfera
    ball.pos = vector(length * sin(angle), 5 - length * cos(angle), 0)
    
    # Atualiza a posição da corda
    rod.axis = ball.pos - rod.pos

