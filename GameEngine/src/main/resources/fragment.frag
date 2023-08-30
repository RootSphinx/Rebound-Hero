#version 330

in vec2 outTexCoord;
uniform sampler2D sampler0;

out vec4 fragColor;

uniform bool UIsign;
uniform vec4 UIcolor;
void main(){

    if(UIsign){
        fragColor = UIcolor;
    }
    else{
        fragColor = texture(sampler0,outTexCoord);
        //fragColor = vec4(1,0,0,texture(sampler0,outTexCoord).a+0.1);
    }

}