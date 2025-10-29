from vpython import *
from math import cos, sin, radians

# Cria a cena
scene = canvas(title='Colisão de duas bolas e chão', width=800, height=600)

# Define as constantes
g = vector(0, -9.8, 0)  # Aceleração da gravidade
v0 = 20  # Velocidade inicial
angle = 45  # Ângulo de lançamento

# Propriedades da bola
raio = 1
raio2 = 2
massa = 1.0
coeficiente_restituicao = 0.8  # Coeficiente de restituição
coeficiente_arrasto = 0.1  # Coeficiente de arrasto

# Calcula as componentes horizontal e vertical da velocidade inicial
v0x = v0 * cos(radians(angle))
v0y = v0 * sin(radians(angle))

# Cria o projétil como uma esfera
bola1 = sphere(pos=vector(-10, 0, 0), radius=raio, color=color.red)
bola2 = sphere(pos=vector(30, 0, 0), radius=raio, color=color.green)

# Cria o chão
chao = box(pos=vector(0, -1, 0), size=vector(60, 0.1, 10), color=color.gray(0.5))

# Define a velocidade inicial do projétil
v1 = vector(v0x, v0y, 0)
v2 = vector(-2, v0y, 0)  # Velocidade inicial da bola verde

# Define o tempo inicial
t = 0

# Variável de controle da animação
animacao_ativa = True

# Loop de animação
while animacao_ativa:
    rate(60)  # Limita a taxa de atualização da animação

    # Atualiza a posição do projétil
    bola1.pos += v1 * 0.01
    bola2.pos += v2 * 0.01

    # Atualiza a velocidade do projétil considerando a gravidade e o arrasto do ar
    forca_arrasto = -coeficiente_arrasto * v1.mag2 * norm(v1)
    v1 += (g + forca_arrasto / massa) * 0.01

    forca_arrasto = -coeficiente_arrasto * v2.mag2 * norm(v2)
    v2 += (g + forca_arrasto / massa) * 0.01

    # Verifica se houve colisão com a bola
    if bola1.pos.x >= bola2.pos.x - bola1.radius:
        v1.x = -v1.x * coeficiente_restituicao  # Inverte e reduz a velocidade na direção x devido à colisão com a bola
        v2.x = -v2.x * coeficiente_restituicao
    #else:
     #   v1.x = v1.x * coeficiente_restituicao

    # Verifica se houve colisão com o chão
    if bola1.pos.y - bola1.radius <= chao.pos.y + chao.size.y / 2:
        v1.y = -v1.y * coeficiente_restituicao  # Inverte e reduz a velocidade na direção y devido à colisão

    if bola2.pos.y - bola2.radius <= chao.pos.y + chao.size.y / 2:
        v2.y = -v2.y * coeficiente_restituicao  # Inverte e reduz a velocidade na direção y devido à colisão
