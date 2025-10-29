import vpython
from vpython import *
from math import cos, sin, radians

scene = canvas(title='Colisão de duas bolas e chão', width=800, height=600)

g = vector(0, -9.8, 0)
v0 = 20
angle = 45

raio = 1

massa1 = 1.0  # Massa da primeira bola
massa2 = 2.0  # Massa da segunda bola

coeficiente_restituicao = 0.8
coeficiente_arrasto = 0.1

v0x = v0 * cos(radians(angle))
v0y = v0 * sin(radians(angle))

bola1 = sphere(pos=vector(-10, 0, 0), radius=raio, color=color.red, mass=massa1)
bola2 = sphere(pos=vector(10, 0, 0), radius=raio, color=color.green, mass=massa2)

chao = box(pos=vector(0, -1, 0), size=vector(60, 0.1, 10), color=color.gray(0.5))

v1 = vector(v0x, v0y, 0)
v2 = vector(-v0x, v0y, 0)

t = 0
animacao_ativa = True

while animacao_ativa:
    rate(60)

    bola1.pos += v1 * 0.01
    bola2.pos += v2 * 0.01

    forca_arrasto1 = -coeficiente_arrasto * v1.mag2 * norm(v1) * bola1.mass
    aceleracao1 = (g + forca_arrasto1) / bola1.mass
    v1 += aceleracao1 * 0.01

    forca_arrasto2 = -coeficiente_arrasto * v2.mag2 * norm(v2) * bola2.mass
    aceleracao2 = (g + forca_arrasto2) / bola2.mass
    v2 += aceleracao2 * 0.01

    if bola1.pos.x >= bola2.pos.x - bola1.radius:
        v1.x = -v1.x * coeficiente_restituicao * bola1.mass / bola2.mass
        v2.x = -v2.x * coeficiente_restituicao * bola2.mass / bola1.mass

    if bola1.pos.y - bola1.radius <= chao.pos.y + chao.size.y / 2:
        v1.y = -v1.y * coeficiente_restituicao

    if bola2.pos.y - bola2.radius <= chao.pos.y + chao.size.y / 2:
        v2.y = -v2.y * coeficiente_restituicao
