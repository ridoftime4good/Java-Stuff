S+e0make
S+i0
S+z0f1-\\S+ \\S+ (\\S(?:\\s*\\S)*) (\\d+):.*
S+f0bcc32 -I%V1\\Include -L%V1\\lib;%V1\\lib\\psdk %FLAGS %FILE
S+j0
S+A0f1-\\S+ \\S+ (\\S(?:\\s*\\S)*) (\\d+):.*
S+g0bcc32 -c -I%V1\\Include %FLAGS %FILE
S+k0
S+B0f1-\\S+ \\S+ (\\S(?:\\s*\\S)*) (\\d+):.*
S+h0
S+l0
S+C0
S+m0%V1Include
S+n0__STDC__ 1\012__cplusplus 1\012_GRASP_IGNORE
S+o0Parse Local Only
S+p0%BASE.exe %ARGS
S+r0%PATH
S+D0
S+w0
S+y0
S+F0
S+10
S+40
S+50
S+I0C:\\Borland\\BCC55
S+J0
S+S0
S+U0PATH+=%V1\\Bin;\012PATH+=%<JGRASP_PATHS>%;
B+a01
B+e00
B+b00
b00
0
B+a00
B+b00
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   e  �J    Q  1              S+e0make %FLAGS
S+i0
S+z0GNU
S+f0g++ %D -g %D %FLAGS %FILE
S+j0
S+A0GNU
S+g0g++ %D -g %D -c %FLAGS %FILE
S+k0
S+B0GNU
S+h0g++ -c -fsyntax-only %FLAGS %FILE
S+l0
S+C0GNU
S+m0/usr/include
S+n0__STDC__ 1\012__cplusplus 1\012__GNUC__ 2\012__GNUG__ 2\012_GRASP_IGNORE
S+o0Parse Local Only
S+p0%MAIN_PATH%SEP%A %CY %ARGS
S+r0%MAIN_PATH
S+D0
S+w0gdb %CYD --args %A %ARGS
S+y0%MAIN_PATH
S+F0f1-\\s*at\\s*(\\S(?:\\s*\\S)*):(\\d+)
S+10
S+40
S+50
S+I0
S+J0
S+S0
S+U0PATH+=%<JGRASP_PATHS>%;\012CYGWIN=nodosfilewarning
B+a01
B+e01
B+b00
0
S+I0
S+J0
S+S0
S+U0PATH+=%<JGRASP_PATHS>%;\012CYGWIN=nodosfilewarning
B+a01
B+e01
B+b00
                                                                                                                                                                                                                                                                                                                                                                                    3   A�a��  �  S+e0NMAKE
S+i0
S+z0f1-(\\S(?:\\s*\\S)*)\\((\\d+)\\).*
S+f0CL  %N /MT %N %D /Zi /MTd %D  %FLAGS %FILE
S+j0
S+A0f1-(\\S(?:\\s*\\S)*)\\((\\d+)\\).*
S+g0Cl /c  %D /Zi %D  %FLAGS %FILE
S+k0
S+B0f1-(\\S(?:\\s*\\S)*)\\((\\d+)\\).*
S+h0Cl /Zs %FLAGS %FILE
S+l0
S+C0f1-(\\S(?:\\s*\\S)*)\\((\\d+)\\).*
S+m0
S+n0__STDC__ 1\012__cplusplus\012_GRASP_IGNORE
S+o0Parse All
S+p0%MAIN_BASE.exe %ARGS
S+r0%MAIN_PATH
S+D0
S+w0
S+y0%MAIN_PATH
S+F0
S+10
S+40
S+50
S+I0C:\\Msdev
S+J0
S+S0
S+U0INCLUDE+=%V1\\include;%V1\\mfc\\include;\012LIB=%V1\\lib;%V1\\mfc\\lib\012PATH+=%V1\\bin;\012PATH+=%<JGRASP_PATHS>%;
B+a01
B+e00
B+b00
b00
  a�A�A�  f             ��^     <
f +   A�!�!�  }             Ď^     �f +   A�!�!�  �            �^     0f 0   a�A�A�  �             �^     �f +   A�!�!�  �            <�^     Lf ,   !�a�A�A��            d�^     �f -   �a�a�  �             ��^     Pf +   A�!�!�  �            ��^     �f ,   a�A�A�  �             ܏^     Hf +   A�!�!�  �     S+e0NMAKE
S+i0
S+z0f1-(\\S(?:\\s*\\S)*)\\((\\d+)\\).*
S+f0CL  %N /MT %N %D /Zi /MTd %D  %FLAGS %FILE
S+j0
S+A0f1-(\\S(?:\\s*\\S)*)\\((\\d+)\\).*
S+g0Cl /c  %D /Zi %D  %FLAGS %FILE
S+k0
S+B0f1-(\\S(?:\\s*\\S)*)\\((\\d+)\\).*
S+h0Cl /Zs %FLAGS %FILE
S+l0
S+C0f1-(\\S(?:\\s*\\S)*)\\((\\d+)\\).*
S+m0
S+n0__STDC__ 1\012__cplusplus\012_GRASP_IGNORE
S+o0Parse All
S+p0%MAIN_BASE.exe %ARGS
S+r0%MAIN_PATH
S+D0
S+w0
S+y0%MAIN_PATH
S+F0
S+10
S+40
S+50
S+I0C:\\Program Files\\Microsoft Visual Studio\\Vc98
S+J0C:\\Program Files\\Microsoft Visual Studio\\Common\\MSDev98
S+S0
S+U0INCLUDE+=%V1\\include;%V1\\mfc\\include;\012LIB=%V1\\lib;%V1\\mfc\\lib\012PATH+=%V1\\bin;%V2\\bin;\012PATH+=%<JGRASP_PATHS>%;
B+a01
B+e00
B+b00
b00
                                                                                                                                                                                                                                                                                                                                                             )�� _xv��E�I� �����m6`�H]��F�2$8)�ͭ(Ң܉�W�!��ߎ�0>�d�/���6U�KM�$�1���mC2dC�i��S���`�c��h��*;|���D�b�4�Ts�?]X�,�G��� �S+e0make %FLAGS
S+i0
S+z0f1-"(.+)", line (\\d+):.*
S+f0CC %D -g %D %FLAGS %FILE
S+j0
S+A0f1-"(.+)", line (\\d+):.*
S+g0CC %D -g %D -c %FLAGS %FILE
S+k0
S+B0f1-"(.+)", line (\\d+):.*
S+h0CC -c -fsyntax-only %FLAGS %FILE
S+l0
S+C0f1-"(.+)", line (\\d+):.*
S+m0/usr/include
S+n0__STDC__ 1\012__cplusplus\012_GRASP_IGNORE
S+o0Parse Local Only
S+p0%MAIN_PATH%SEP%A %ARGS
S+r0%MAIN_PATH
S+D0
S+w0dbx %A
S+y0%MAIN_PATH
S+F0f1-\\s*at\\s*(\\S(?:\\s*\\S)*):(\\d+)
S+10
S+40
S+50
S+I0
S+J0
S+S0
S+U0PATH+=%<JGRASP_PATHS>%;
B+a01
B+e00
B+b00
                                                                                                                                                                                                                                                                                                                                                                                                                                                          S+e0make %FLAGS
S+i0
S+z0GNU
S+f0g++ %D -g %D %FLAGS %FILE -lglu32 -lglut32 -lopengl32
S+j0
S+A0GNU
S+g0g++ %D -g %D -c %FLAGS %FILE
S+k0
S+B0GNU
S+h0g++ -c -fsyntax-only %FLAGS %FILE
S+l0
S+C0GNU
S+m0/usr/include
S+n0__STDC__ 1\012__cplusplus 1\012__GNUC__ 2\012__GNUG__ 2\012_GRASP_IGNORE\012
S+o0Parse Local Only
S+p0%MAIN_PATH%SEP%A %CY %ARGS
S+r0%MAIN_PATH
S+D0
S+w0gdb %CYD --args %A %ARGS
S+y0%MAIN_PATH
S+F0f1-\\s*at\\s*(\\S(?:\\s*\\S)*):(\\d+)
S+10
S+40
S+50
S+I0
S+J0
S+S0
S+U0PATH+=%<JGRASP_PATHS>%;\012CYGWIN=nodosfilewarning
B+a01
B+e01
B+b00
0
S+I0
S+J0
S+S0
S+U0PATH+=%<JGRASP_PATHS>%;\012CYGWIN=nodosfilewarning
B+a01
B+e01
B+b00
                                                                                                                                                                                                                                                                                                                                                                                                                                                    S+e0make %FLAGS
S+i0
S+z0GNU
S+f0g++ %D -g %D %FLAGS %FILE
S+j0
S+A0GNU
S+g0g++ %D -g %D -c %FLAGS %FILE
S+k0
S+B0GNU
S+h0g++ -c -fsyntax-only %FLAGS %FILE
S+l0
S+C0GNU
S+m0/usr/include
S+n0__STDC__ 1\012__cplusplus 1\012__GNUC__ 2\012__GNUG__ 2\012_GRASP_IGNORE
S+o0Parse Local Only
S+p0%MAIN_PATH%SEP%A %CY %ARGS
S+r0%MAIN_PATH
S+D0
S+w0gdb %CYD --args %A %ARGS
S+y0%MAIN_PATH
S+F0f1-\\s*at\\s*(\\S(?:\\s*\\S)*):(\\d+)
S+10
S+40
S+50
S+I0
S+J0
S+S0
S+U0PATH+=%<JGRASP_PATHS>%;\012PATH+=C:\\cygwin\\bin;\012CYGWIN=nodosfilewarning
B+a01
B+e01
B+b00
=C:\\cygwin\\bin;\012PATH+=%<JGRASP_PATHS>%;\012CYGWIN=nodosfilewarning
B+a01
B+e01
B+b00
                                                                                                                                                                                                                                                                                                                                                                                                                                              S+e0make %FLAGS
S+i0
S+z0GNU
S+f0c++ %D -g %D %FLAGS %FILE
S+j0
S+A0GNU
S+g0c++ %D -g %D -c %FLAGS %FILE
S+k0
S+B0GNU
S+h0c++ -c -fsyntax-only %FLAGS %FILE
S+l0
S+C0GNU
S+m0/usr/include
S+n0__STDC__ 1\012__cplusplus 1\012__GNUC__ 2\012__GNUG__ 2\012_GRASP_IGNORE
S+o0Parse Local Only
S+p0%MAIN_PATH%SEP%A %CY %ARGS
S+r0%MAIN_PATH
S+D0
S+w0gdb %CYD --args %A %ARGS
S+y0%MAIN_PATH
S+F0f1-\\s*at\\s*(\\S(?:\\s*\\S)*):(\\d+)
S+10
S+40
S+50
S+I0
S+J0
S+S0
S+U0PATH+=%<JGRASP_PATHS>%;
B+a01
B+e00
B+b00
/usr/include
S+n0__STDC__ 1\012__cplusplus 1\012__GNUC__ 2\012__GNUG__ 2\012_GRASP_IGNORE
S+o0Parse Local Only
S+p0%MAIN_PATH%SEP%A %CY %ARGS
S+r0%MAIN_PATH
S+D0
S+w0gdb %A
S+y0%MAIN_PATH
S+F0f1-\\s*at\\s*(\\S(?:\\s*\\S)*):(\\d+)
S+10
S+40
S+50
S+I0
S+J0
S+S0
S+U0PATH+=%<JGRASP_PATHS>%;
B+a01
B+e00
B+b00
                                                                                                                                                                                                                                                                                           S+e0make %FLAGS
S+i0
S+z0GNU
S+f0gxx %D -g %D %FLAGS %FILE
S+j0
S+A0GNU
S+g0gxx %D -g %D -c %FLAGS %FILE
S+k0
S+B0GNU
S+h0
S+l0
S+C0
S+m0/usr/include
S+n0__STDC__ 1\012__cplusplus 1\012__GNUC__ 2\012__GNUG__ 2\012_GRASP_IGNORE
S+o0Parse Local Only
S+p0%MAIN_PATH%SEP%A %ARGS
S+r0%MAIN_PATH
S+D0
S+w0gdb %CD --args %A %ARGS
S+y0%MAIN_PATH
S+F0f1-\\s*at\\s*(\\S(?:\\s*\\S)*):(\\d+)
S+10
S+40
S+50
S+I0
S+J0
S+S0To use DJGPP environment in jGRASP only,\012make environment like:\012PATH+=C:\\djgpp\\bin;\012DJGPP=C:\\djgpp\\djgpp.env
S+U0PATH+=%<JGRASP_PATHS>%;
B+a01
B+e01
B+b00
\\s*(\\S(?:\\s*\\S)*):(\\d+)
S+10
S+40
S+50
S+I0
S+J0
S+S0To use DJGPP environment in jGRASP only,\012make environment like:\012PATH+=C:\\djgpp\\bin;\012DJGPP=C:\\djgpp\\djgpp.env
S+U0PATH+=%<JGRASP_PATHS>%;
B+a01
B+e01
B+b00
                                                                                                                                                                                                                                                                                                                                                                     S+e0make %FLAGS
S+i0
S+z0f1-(\\S(?:\\s*\\S)*):(\\d+):.*
S+f0gxx %D -g %D %FLAGS %FILE
S+j0
S+A0f1-(\\S(?:\\s*\\S)*):(\\d+):.*
S+g0gxx %D -g %D -c %FLAGS %FILE
S+k0
S+B0f1-(\\S(?:S+e0make %FLAGS
S+i0
S+z0GNU
S+f0gxx %D -g %D %FLAGS %FILE
S+j0
S+A0GNU
S+g0gxx %D -g %D -c %FLAGS %FILE
S+k0
S+B0GNU
S+h0
S+l0
S+C0
S+m0/usr/include
S+n0__STDC__ 1\012__cplusplus 1\012__GNUC__ 2\012__GNUG__ 2\012_GRASP_IGNORE
S+o0Parse Local Only
S+p0%MAIN_PATH%SEP%A %ARGS
S+r0%MAIN_PATH
S+D0
S+w0gdb %CD --args %A %ARGS
S+y0%MAIN_PATH
S+F0f1-\\s*at\\s*(\\S(?:\\s*\\S)*):(\\d+)
S+10
S+40
S+50
S+I0
S+J0
S+S0
S+U0PATH+=%<JGRASP_PATHS>%;\012PATH+=C:\\djgpp\\bin;\012DJGPP=C:\\djgpp\\djgpp.env
B+a01
B+e01
B+b00
 %ARGS
S+r0%MAIN_PATH
S+D0
S+w0gdb %A
S+y0%MAIN_PATH
S+F0f1-\\s*at\\s*(\\S(?:\\s*\\S)*):(\\d+)
S+10
S+40
S+50
S+I0
S+J0
S+S0
S+U0PATH+=C:\\djgpp\\bin;\012PATH+=%<JGRASP_PATHS>%;\012DJGPP=C:\\djgpp\\djgpp.env
B+a01
B+e01
B+b00
                                                                                                                                                                                                                                                                                                                                                                       S+e0make %FLAGS
S+i0
S+z0GNU
S+f0g++ %D -g %D %FLAGS %FILE
S+j0
S+A0GNU
S+g0g++ %D -g %D -c %FLAGS %FILE
S+k0
S+B0GNU
S+h0g++ -c -fsyntax-only %FLAGS %FILE
S+l0
S+C0GNU
S+m0/usr/include
S+n0__STDC__ 1\012__cplusplus 1\012__GNUC__ 2\012__GNUG__ 2\012_GRASP_IGNORE
S+o0Parse Local Only
S+p0%MAIN_PATH%SEP%A %ARGS
S+r0%MAIN_PATH
S+D0
S+w0gdb %CD --args %A %ARGS
S+y0%MAIN_PATH
S+F0f1-\\s*at\\s*(\\S(?:\\s*\\S)*):(\\d+)
S+10
S+40
S+50
S+I0
S+J0
S+S0
S+U0PATH+=%<JGRASP_PATHS>%;
B+a01
B+e01
B+b00
:\\s*\\S)*):(\\d+)
S+10
S+40
S+50
S+I0
S+J0
S+S0
S+U0PATH+=%<JGRASP_PATHS>%;
B+a01
B+e01
B+b00
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           S+e0make %FLAGS
S+i0
S+z0GNU
S+f0g++ %D -g %D %FLAGS %FILE
S+j0
S+A0GNU
S+g0g++ %D -g %D -c %FLAGS %FILE
S+k0
S+B0GNU
S+h0g++ -c -fsyntax-only %FLAGS %FILE
S+l0
S+C0GNU
S+m0/usr/include
S+n0__STDC__ 1\012__cplusplus 1\012__GNUC__ 2\012__GNUG__ 2\012_GRASP_IGNORE
S+o0Parse Local Only
S+p0%MAIN_PATH%SEP%A %ARGS
S+r0%MAIN_PATH
S+D0
S+w0gdb %CD --args %A %ARGS
S+y0%MAIN_PATH
S+F0f1-\\s*at\\s*(\\S(?:\\s*\\S)*):(\\d+)
S+10
S+40
S+50
S+I0
S+J0
S+S0
S+U0PATH+=%<JGRASP_PATHS>%;\012PATH+=C:\\mingw\\bin;
B+a01
B+e01
B+b00
TDC__ 1\012__cplusplus 1\012__GNUC__ 2\012__GNUG__ 2\012_GRASP_IGNORE
S+o0Parse Local Only
S+p0%MAIN_PATH%SEP%A %CY %ARGS
S+r0%MAIN_PATH
S+D0
S+w0gdb %A
S+y0%MAIN_PATH
S+F0f1-\\s*at\\s*(\\S(?:\\s*\\S)*):(\\d+)
S+10
S+40
S+50
S+I0
S+J0
S+S0
S+U0PATH+=C:\\mingw\\bin;\012PATH+=%<JGRASP_PATHS>%;
B+a01
B+e01
B+b00
                                                                                                                                                                                                                                                                                                                                                                                                            S+e0make %FLAGS
S+i0
S+z0GNU
S+f0g++ %D -g %D %FLAGS %FILE
S+j0
S+A0GNU
S+g0g++ %D -g %D -c %FLAGS %FILE
S+k0
S+B0GNU
S+h0g++ -c -fsyntax-only %FLAGS %FILE
S+l0
S+C0GNU
S+m0/usr/include
S+n0__STDC__ 1\012__cplusplus 1\012__GNUC__ 2\012__GNUG__ 2\012_GRASP_IGNORE
S+o0Parse Local Only
S+p0%MAIN_PATH%SEP%A %CY %ARGS
S+r0%MAIN_PATH
S+D0
S+w0insight --args %A %ARGS
S+y0%MAIN_PATH
S+F0f1-\\s*at\\s*(\\S(?:\\s*\\S)*):(\\d+)
S+10
S+40
S+50
S+I0
S+J0
S+S0
S+U0PATH+=%<JGRASP_PATHS>%;\012PATH+=C:\\cygwin\\bin;\012CYGWIN=nodosfilewarning
B+a01
B+e01
B+b00
PATH+=C:\\cygwin\\bin;\012PATH+=%<JGRASP_PATHS>%;\012CYGWIN=nodosfilewarning
B+a01
B+e01
B+b00
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              S+e0make %FLAGS
S+i0
S+z0GNU
S+f0g++ %D -g %D %FLAGS %FILE
S+j0
S+A0GNU
S+g0g++ %D -g %D -c %FLAGS %FILE
S+k0
S+B0GNU
S+h0g++ -c -fsyntax-only %FLAGS %FILE
S+l0
S+C0GNU
S+m0/usr/include
S+n0__STDC__ 1\012__cplusplus 1\012__GNUC__ 2\012__GNUG__ 2\012_GRASP_IGNORE
S+o0Parse Local Only
S+p0%MAIN_PATH%SEP%A %CY %ARGS
S+r0%MAIN_PATH
S+D0
S+w0insight --args %A %ARGS
S+y0%MAIN_PATH
S+F0f1-\\s*at\\s*(\\S(?:\\s*\\S)*):(\\d+)
S+10
S+40
S+50
S+I0
S+J0
S+S0
S+U0PATH+=%<JGRASP_PATHS>%;\012CYGWIN=nodosfilewarning
B+a01
B+e01
B+b00
0
S+50
S+I0
S+J0
S+S0
S+U0PATH+=%<JGRASP_PATHS>%;\012CYGWIN=nodosfilewarning
B+a01
B+e01
B+b00
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    S+e0NMAKE
S+i0
S+z0f1-(\\S(?:\\s*\\S)*)\\((\\d+)\\).*
S+f0CL  %N /MT %N  %D /Zi /MTd %D  %FLAGS %FILE
S+j0
S+A0f1-(\\S(?:\\s*\\S)*)\\((\\d+)\\).*
S+g0Cl /c  %D /Zi %D  %FLAGS %FILE
S+k0
S+B0f1-(\\S(?:\\s*\\S)*)\\((\\d+)\\).*
S+h0Cl /Zs %FLAGS %FILE
S+l0
S+C0f1-(\\S(?:\\s*\\S)*)\\((\\d+)\\).*
S+m0/usr/include
S+n0__STDC__ 1\012_GRASP_IGNORE
S+o0Parse Local Only
S+p0%MAIN_BASE.exe %ARGS
S+r0%MAIN_PATH
S+D0
S+w0
S+y0%MAIN_PATH
S+F0
S+10
S+40
S+50
S+I0C:\\Program Files\\Microsoft Visual Studio 8\\VC
S+J0C:\\Program Files\\Microsoft Visual Studio 8\\Common7\\IDE
S+S0
S+U0INCLUDE+=%V1\\include;\012LIB=%V1\\lib\012PATH+=%V1\\bin;%V2;\012PATH+=%<JGRASP_PATHS>%;
B+a01
B+e00
B+b00
b00
