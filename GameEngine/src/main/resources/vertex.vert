#version 330

layout(location = 0) in vec2 position;
layout(location = 1) in vec2 texCoord;

out vec2 outTexCoord;

uniform mat4 matrix;

void main(){
    gl_Position = matrix * vec4(position,0,1.0f);
    outTexCoord = texCoord;
}