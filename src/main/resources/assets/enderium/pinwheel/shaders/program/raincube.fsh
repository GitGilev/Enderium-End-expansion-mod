#include veil:fog

uniform sampler2D Sampler0;

uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;
uniform vec2 ScreenSize;

in float vertexDistance;
in vec4 vertexColor;
in vec2 texCoord0;

out vec4 fragColor;

vec3 hsl2rgb(in vec3 c)
{
    vec3 rgb = clamp(abs(mod(c.x * 6.0 + vec3(0.0, 4.0, 2.0), 6.0) - 3.0) - 1.0, 0.0, 1.0);
    return c.z + c.y * (rgb - 0.5) * (1.0 - abs(2.0 * c.z - 1.0));
}

vec3 rgb2hsl(in vec3 c)
{
    float h = 0.0;
    float s = 0.0;
    float l = (max(c.r, max(c.g, c.b)) + min(c.r, min(c.g, c.b))) / 2.0;
    if (max(c.r, max(c.g, c.b)) > min(c.r, min(c.g, c.b)))
    {
        float d = max(c.r, max(c.g, c.b)) - min(c.r, min(c.g, c.b));
        s = l < 0.5 ? d / (max(c.r, max(c.g, c.b)) + min(c.r, min(c.g, c.b))) : d / (2.0 - max(c.r, max(c.g, c.b)) - min(c.r, min(c.g, c.b)));
        if (c.r == max(c.r, max(c.g, c.b))) h = (c.g - c.b) / d;
        else if (c.g == max(c.g, max(c.r, c.b))) h = 2.0 + (c.b - c.r) / d;
        else h = 4.0 + (c.r - c.g) / d;
        if (h < 0.0) h += 6.0;
        h /= 6.0;
    }
    return vec3(h, s, l);
}

float gaussian(float x, float sigma)
{
    return exp(-x * x / (2.0 * sigma * sigma)) / (sqrt(2.0 * 3.1415) * sigma);
}

float bloomEffect(vec2 st, float size)
{
    vec2 center = vec2(0.5); // Center of the square
    vec2 distToEdge = abs(st - center) - vec2((size/2.))-0.134; // Distance to the square's edges

    // We want to measure the maximum distance from the edges, so we focus on both x and y
    float edgeDist = max(distToEdge.x, distToEdge.y);
    
    // Apply Gaussian blur based on the distance from the edge of the square
    float blurAmount = gaussian(edgeDist, 0.058); // Adjust the sigma for the smoothness of the bloom
    return blurAmount;
}

void main() {
    vec2 st = gl_FragCoord.xy / ScreenSize.xy;

    float size = 0.925; // Size of the square (adjustable)
    float square = step(size/2., max(abs(st.x - 0.5), abs(st.y - 0.5))); // Define the square boundaries
    vec3 color = vec3(0.0); // Initial black square
    
    // Apply bloom around the square's edges
    float bloom = bloomEffect(st, size);
    
    float y = smoothstep(0.0, 3.0, st.x + st.y);
    vec3 rainbow = hsl2rgb(vec3(y / 2.5, 1.0, 0.65));
    
    if (square < 1.0) {
        color += bloom * vec3(rainbow); // Add bloom effect around all sides
    }
    
    // Final color output
    rainbow = vec3(rainbow * square + color);
    vec4 color4 = vec4(rainbow, 1.0);
    fragColor = linear_fog(color4, vertexDistance, FogStart, FogEnd, FogColor);
}