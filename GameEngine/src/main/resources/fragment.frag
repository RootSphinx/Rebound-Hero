#version 330

in vec2 outTexCoord;
uniform sampler2D sampler0;

out vec4 fragColor;

void main(){

    fragColor = texture(sampler0,outTexCoord);
    //fragColor = vec4(1,1,1,texture(sampler0,outTexCoord).a);
}