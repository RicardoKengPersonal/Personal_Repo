from vpython import *
from math import cos, sin, radians

# Cria a cena
scene = canvas(title='Colisão de Bola com Parede e Chão', width=800, height=600)

# Define as constantes
g = vector(0, -9.8, 0)  # Aceleração da gravidade
v0 = 20  # Velocidade inicial
angle = 45  # Ângulo de lançamento
d = 10  # Distância da parede

# Propriedades da bola
raio = 0.5
massa = 1.0
coeficiente_restituicao = 0.8  # Coeficiente de restituição
coeficiente_arrasto = 0.1  # Coeficiente de arrasto

# Calcula as componentes horizontal e vertical da velocidade inicial
v0x = v0 * cos(radians(angle))
v0y = v0 * sin(radians(angle))

# Cria o projétil como uma esfera
proj = sphere(pos=vector(0, 0, 0), radius=raio, color=color.red)

# Cria a parede
parede = box(pos=vector(d, 0, 0), size=vector(0.2, 15, 10), color=color.green)

# Cria o chão
chao = box(pos=vector(0, -1, 0), size=vector(80, 0.1, 10), color=color.gray(0.5))

# Define a velocidade inicial do projétil
v = vector(v0x, v0y, 0)

# Define o tempo inicial
t = 0

# Variável de controle da animação
animacao_ativa = True

# Variáveis de controle do tempo
tempo_inicio_reinicio = -1
reinicio_solicitado = False

# Função para reiniciar a animação
def reiniciar_animacao():
    global animacao_ativa, tempo_inicio_reinicio, reinicio_solicitado
    animacao_ativa = True
    tempo_inicio_reinicio = -1
    proj.pos = vector(0, 0, 0)
    v.x = v0x
    v.y = v0y
    reinicio_solicitado = False

# Manipulador de eventos do teclado
def entrada_teclado(evt):
    global reinicio_solicitado
    if evt.key == 'r' or evt.key == 'R':
        reinicio_solicitado = True

# Vincula o manipulador de eventos do teclado
scene.bind('keydown', entrada_teclado)

# Loop de animação
while animacao_ativa:
    rate(60)  # Limita a taxa de atualização da animação

    # Verifica se houve solicitação de reinício
    if reinicio_solicitado:
        reiniciar_animacao()

    # Atualiza a posição do projétil
    proj.pos += v * 0.01

    # Atualiza a velocidade do projétil considerando a gravidade e o arrasto do ar
    forca_arrasto = -coeficiente_arrasto * v.mag2 * norm(v)
    v += (g + forca_arrasto / massa) * 0.01

    # Verifica se houve colisão com a parede
    if proj.pos.x >= parede.pos.x - proj.radius:
        v.x = -v.x * coeficiente_restituicao  # Inverte e reduz a velocidade na direção x devido à colisão com a parede

    # Verifica se houve colisão com o chão
    if proj.pos.y - proj.radius <= chao.pos.y + chao.size.y / 2:
        v.y = -v.y * coeficiente_restituicao  # Inverte e reduz a velocidade na direção y devido à colisão com o chão
        if v.y < 1:
            if tempo_inicio_reinicio == -1:
                tempo_inicio_reinicio = time.time()  # Marca o tempo de início do reinício
            elif time.time() - tempo_inicio_reinicio > 2:  # Para a animação após a bola atingir o chão duas vezes
                reiniciar_animacao()
                continue

    # Verifica se a animação deve ser reiniciada
    if tempo_inicio_reinicio != -1 and time.time() - tempo_inicio_reinicio > 2:
        reiniciar_animacao()
