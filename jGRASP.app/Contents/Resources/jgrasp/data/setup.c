SCe0make
SCi0
SCz0f1-\\S+ \\S+ (\\S(?:\\s*\\S)*) (\\d+):.*
SCf0bcc32 -I%V1\\Include -L%V1\\lib;%V1\\lib\\psdk %FLAGS %FILE
SCj0
SCA0f1-\\S+ \\S+ (\\S(?:\\s*\\S)*) (\\d+):.*
SCg0bcc32 -c -I%V1\\Include %FLAGS %FILE
SCk0
SCB0f1-\\S+ \\S+ (\\S(?:\\s*\\S)*) (\\d+):.*
SCh0
SCl0
SCC0
SCm0%V1Include
SCn0__STDC__ 1\012_GRASP_IGNORE
SCo0Parse Local Only
SCp0%BASE.exe %ARGS
SCr0%PATH
SCD0
SCw0
SCy0
SCF0
SC10
SC40
SC50
SCI0C:\\Borland\\BCC55
SCJ0
SCS0
SCU0PATH+=%<JGRASP_PATHS>%;\012PATH+=%V1\\bin;
BCa01
BCe00
BCc01
c01
ARGS
SCr0%PATH
SCD0
SCw0
SCy0
SCF0
SCI0
SCJ0
BCa01
Cx0
SCy0
SCF0
BCa01
                                                                                                                                                                                                                                                                                                                                                                                                                                                           �  X!H    ?                         SYSTEM_AUDIT_ACE    
      ,�J    �  J   @                       SCe0make %FLAGS
SCi0
SCz0GNU
SCf0gcc %D -g %D %FLAGS %FILE
SCj0
SCA0GNU
SCg0gcc %D -g %D -c %FLAGS %FILE
SCk0
SCB0GNU
SCh0gcc -c -fsyntax-only %FLAGS %FILE
SCl0
SCC0GNU
SCm0/usr/include
SCn0__STDC__ 1\012__GNUC__ 2\012_GRASP_IGNORE
SCo0Parse Local Only
SCp0%MAIN_PATH%SEP%A %CY %ARGS
SCr0%MAIN_PATH
SCD0
SCw0gdb %CYD --args %A %ARGS
SCy0%MAIN_PATH
SCF0f1-\\s*at\\s*(\\S(?:\\s*\\S)*):(\\d+)
SC10
SC40
SC50
SCI0
SCJ0
SCS0
SCU0PATH+=%<JGRASP_PATHS>%;\012CYGWIN=nodosfilewarning
BCa01
BCe01
BCc01
0
SCI0
SCJ0
SCS0
SCU0PATH+=%<JGRASP_PATHS>%;\012CYGWIN=nodosfilewarning
BCa01
BCe01
BCc01
                                                                                                                                                                                                                                                                                                                                   Hug ;   A�!��!��  T          
   4�^     �vg ;   A�SCe0NMAKE
SCi0
SCz0f1-(\\S(?:\\s*\\S)*)\\((\\d+)\\).*
SCf0CL  %N /MT %N  %D /Zi /MTd %D  %FLAGS %FILE
SCj0
SCA0f1-(\\S(?:\\s*\\S)*)\\((\\d+)\\).*
SCg0Cl /c  %D /Zi %D  %FLAGS %FILE
SCk0
SCB0f1-(\\S(?:\\s*\\S)*)\\((\\d+)\\).*
SCh0Cl /Zs %FLAGS %FILE
SCl0
SCC0f1-(\\S(?:\\s*\\S)*)\\((\\d+)\\).*
SCm0/usr/include
SCn0__STDC__ 1\012_GRASP_IGNORE
SCo0Parse Local Only
SCp0%MAIN_BASE.exe %ARGS
SCr0%MAIN_PATH
SCD0
SCw0
SCy0%MAIN_PATH
SCF0
SC10
SC40
SC50
SCI0C:\\Program Files\\Microsoft Visual Studio\\Vc98
SCJ0C:\\Program Files\\Microsoft Visual Studio\\Common\\MSDev98
SCS0
SCU0INCLUDE+=%V1\\include;%V1\\mfc\\include;\012LIB=%V1\\lib;%V1\\mfc\\lib\012PATH+=%V1\\bin;%V2\\bin;\012PATH+=%<JGRASP_PATHS>%;
BCa01
BCe00
BCc01
c01
                                                                                                                                                                                                                                                                                SCe0
SCi0
SCz0
SCf0CL /MT %FLAGS %FILE
SCj0
SCA0f1-(\\S(?:\\S|\\s\\S)*)\\((\\d+)\\).*
SCg0Cl /c %FLAGS %FILE
SCk0
SCB0f1-(\\S(?:\\S|\\s\\S)*)\\((\\d+)\\).*
SCh0Cl /Zs %FLAGS %FILE
SCl0
SCC0f1-(\\S(?:\\S|\\s\\S)*)\\SCe0NMAKE
SCi0
SCz0f1-(\\S(?:\\s*\\S)*)\\((\\d+)\\).*
SCf0CL  %N /MT %N %D /Zi /MTd %D  %FLAGS %FILE
SCj0
SCA0f1-(\\S(?:\\s*\\S)*)\\((\\d+)\\).*
SCg0Cl /c  %D /Zi %D  %FLAGS %FILE
SCk0
SCB0f1-(\\S(?:\\s*\\S)*)\\((\\d+)\\).*
SCh0Cl /Zs %FLAGS %FILE
SCl0
SCC0f1-(\\S(?:\\s*\\S)*)\\((\\d+)\\).*
SCm0/usr/include
SCn0__STDC__ 1\012_GRASP_IGNORE
SCo0Parse Local Only
SCp0%MAIN_BASE.exe %ARGS
SCr0%MAIN_PATH
SCD0
SCw0
SCy0%MAIN_PATH
SCF0
SC10
SC40
SC50
SCI0C:\\Msdev
SCJ0
SCS0
SCU0INCLUDE+=%V1\\include;%V1\\mfc\\include;\012LIB=%V1\\lib;%V1\\mfc\\lib\012PATH+=%V1\\bin;\012PATH+=%<JGRASP_PATHS>%;
BCa01
BCe00
BCc01
c01
a.util.ArrayList)	=>	a
	LineNumbersOf: grasp.gui.template.Template$Item
		161	=>	0, 1, 2, 3, 4, 5, 6 and 7

Class: public grasp.gui.template.Template$TMenuItem	=>	b.b.b.d
	Source: Template.java
	FieldsOf: grasp.gui.template.Template$TMenuItem
		public lines	=>	a
	MethodsOf: grasp.gui.template.Template$TMenuItem
	LineNumbersOf: grasp.gui.template.Template$TMenuItem
		1SCe0make %FLAGS
SCi0
SCz0f1-"(.+)", line (\\d+):.*
SCf0cc %D -g %D %FLAGS %FILE
SCj0
SCA0f1-"(.+)", line (\\d+):.*
SCg0cc %D -g %D -c %FLAGS %FILE
SCk0
SCB0f1-"(.+)", line (\\d+):.*
SCh0cc -c -fsyntax-only %FLAGS %FILE
SCl0
SCC0f1-"(.+)", line (\\d+):.*
SCm0/usr/include
SCn0__STDC__ 1\012_GRASP_IGNORE
SCo0Parse Local Only
SCp0%MAIN_PATH%SEP%A %ARGS
SCr0%MAIN_PATH
SCD0
SCw0dbx %A
SCy0%MAIN_PATH
SCF0f1-\\s*at\\s*(\\S(?:\\s*\\S)*):(\\d+)
SC10
SC40
SC50
SCI0
SCJ0
SCS0
SCU0PATH+=%<JGRASP_PATHS>%;
BCa01
BCe00
BCc01
                                                                                                                                                                                                                                                                                                                                                                                                                                           SCe0make %FLAGS
SCi0
SCz0GNU
SCf0gcc %D -g %D %FLAGS %FILE -lglu32 -lglut32 -lopengl32
SCj0
SCA0GNU
SCg0gcc %D -g %D -c %FLAGS %FILE
SCk0
SCB0GNU
SCh0gcc -c -fsyntax-only %FLAGS %FILE
SCl0
SCC0GNU
SCm0/usr/include
SCn0__STDC__ 1\012__GNUC__ 2\012_GRASP_IGNORE
SCo0Parse Local Only
SCp0%MAIN_PATH%SEP%A %CY %ARGS
SCr0%MAIN_PATH
SCD0
SCw0gdb %CYD --args %A %ARGS
SCy0%MAIN_PATH
SCF0f1-\\s*at\\s*(\\S(?:\\s*\\S)*):(\\d+)
SC10
SC40
SC50
SCI0
SCJ0
SCS0
SCU0PATH+=%<JGRASP_PATHS>%;\012CYGWIN=nodosfilewarning
BCa01
BCe01
BCc01
0
SCI0
SCJ0
SCS0
SCU0PATH+=%<JGRASP_PATHS>%;\012CYGWIN=nodosfilewarning
BCa01
BCe01
BCc01
                                                                                                                                                                                                                                                                                                                                                                                                                                                         SCe0make %FLAGS
SCi0
SCz0GNU
SCf0gcc %D -g %D %FLAGS %FILE
SCj0
SCA0GNU
SCg0gcc %D -g %D -c %FLAGS %FILE
SCk0
SCB0GNU
SCh0gcc -c -fsyntax-only %FLAGS %FILE
SCl0
SCC0GNU
SCm0/usr/include
SCn0__STDC__ 1\012__GNUC__ 2\012_GRASP_IGNORE
SCo0Parse Local Only
SCp0%MAIN_PATH%SEP%A %CY %ARGS
SCr0%MAIN_PATH
SCD0
SCw0gdb %CYD --args %A %ARGS
SCy0%MAIN_PATH
SCF0f1-\\s*at\\s*(\\S(?:\\s*\\S)*):(\\d+)
SC10
SC40
SC50
SCI0
SCJ0
SCS0
SCU0PATH+=%<JGRASP_PATHS>%;\012PATH+=C:\\cygwin\\bin;\012CYGWIN=nodosfilewarning
BCa01
BCe01
BCc01
=C:\\cygwin\\bin;\012PATH+=%<JGRASP_PATHS>%;\012CYGWIN=nodosfilewarning
BCa01
BCe01
BCc01
                                                                                                                                                                                                                                                                      �       �       �       �       )      .      @      E      h      m      x      �      �      �      �      �      �     SCe0make %FLAGS
SCi0
SCz0GNU
SCf0cc %D -g %D %FLAGS %FILE
SCj0
SCA0GNU
SCg0cc %D -g %D -c %FLAGS %FILE
SCk0
SCB0GNU
SCh0cc -c -fsyntax-only %FLAGS %FILE
SCl0
SCC0GNU
SCm0/usr/include
SCn0__STDC__ 1\012__GNUC__ 2\012_GRASP_IGNORE
SCo0Parse Local Only
SCp0%MAIN_PATH%SEP%A %CY %ARGS
SCr0%MAIN_PATH
SCD0
SCw0gdb %CYD --args %A %ARGS
SCy0%MAIN_PATH
SCF0f1-\\s*at\\s*(\\S(?:\\s*\\S)*):(\\d+)
SC10
SC40
SC50
SCI0
SCJ0
SCS0
SCU0PATH+=%<JGRASP_PATHS>%;
BCa01
BCe00
BCc01
\\S)*):(\\d+)
SC10
SC40
SC50
SCI0
SCJ0
SCS0
SCU0PATH+=%<JGRASP_PATHS>%;
BCa01
BCe00
BCc01
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 SCe0make %FLAGS
SCi0
SCz0GNU
SCf0gcc %D -g %D %FLAGS %FILE
SCj0
SCA0GNU
SCg0gcc %D -g %D -c %FLAGS %FILE
SCk0
SCB0GNU
SCh0
SCl0
SCC0
SCm0/usr/include
SCn0__STDC__ 1\012__cplusplus 1\012__GNUC__ 2\012__GNUG__ 2\012_GRASP_IGNORE
SCo0Parse Local Only
SCp0%MAIN_PATH%SEP%A %ARGS
SCr0%MAIN_PATH
SCD0
SCw0gdb %CD --args %A %ARGS
SCy0%MAIN_PATH
SCF0f1-\\s*at\\s*(\\S(?:\\s*\\S)*):(\\d+)
SC10
SC40
SC50
SCI0
SCJ0
SCS0To use DJGPP environment in jGRASP only,\012make environment like:\012PATH+=C:\\djgpp\\bin;\012DJGPP=C:\\djgpp\\djgpp.env
SCU0PATH+=%<JGRASP_PATHS>%;
BCa01
BCe01
BCc01
\djgpp\\djgpp.env
SCU0PATH+=%<JGRASP_PATHS>%;
BCa01
BCe01
BCc01
djgpp.env
BCa01
BCe01
BCc01
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             SCe0make %FLAGS
SCi0
SCz0GNU
SCf0gcc %D -g %D %FLAGS %FILE
SCj0
SCA0GNU
SCg0gcc %D -g %D -c %FLAGS %FILE
SCk0
SCB0GNU
SCh0
SCl0
SCC0
SCm0/usr/include
SCn0__STDC__ 1\012__cplusplus 1\012__GNUC__ 2\012__GNUG__ 2\012_GRASP_IGNORE
SCo0Parse Local Only
SCp0%MAIN_PATH%SEP%A %ARGS
SCr0%MAIN_PATH
SCD0
SCw0gdb %CD --args %A %ARGS
SCy0%MAIN_PATH
SCF0f1-\\s*at\\s*(\\S(?:\\s*\\S)*):(\\d+)
SC10
SC40
SC50
SCI0
SCJ0
SCS0
SCU0PATH+=%<JGRASP_PATHS>%;\012PATH+=C:\\djgpp\\bin;\012DJGPP=C:\\djgpp\\djgpp.env
BCa01
BCe01
BCc01
JGRASP_PATHS>%;\012DJGPP=C:\\djgpp\\djgpp.env
BCa01
BCe01
BCc01
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         SCe0make %FLAGS
SCi0
SCz0GNU
SCf0gcc %D -g %D %FLAGS %FILE
SCj0
SCA0GNU
SCg0gcc %D -g %D -c %FLAGS %FILE
SCk0
SCB0GNU
SCh0gcc -c -fsyntax-only %FLAGS %FILE
SCl0
SCC0GNU
SCm0/usr/include
SCn0__STDC__ 1\012__GNUC__ 2\012_GRASP_IGNORE
SCo0Parse Local Only
SCp0%MAIN_PATH%SEP%A %ARGS
SCr0%MAIN_PATH
SCD0
SCw0gdb %CD --args %A %ARGS
SCy0%MAIN_PATH
SCF0f1-\\s*at\\s*(\\S(?:\\s*\\S)*):(\\d+)
SC10
SC40
SC50
SCI0
SCJ0
SCS0
SCU0PATH+=%<JGRASP_PATHS>%;
BCa01
BCe01
BCc01
:\\s*\\S)*):(\\d+)
SC10
SC40
SC50
SCI0
SCJ0
SCS0
SCU0PATH+=%<JGRASP_PATHS>%;
BCa01
BCe01
BCc01
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            SCe0make %FLAGS
SCi0
SCz0GNU
SCf0gcc %D -g %D %FLAGS %FILE
SCj0
SCA0GNU
SCg0gcc %D -g %D -c %FLAGS %FILE
SCk0
SCB0GNU
SCh0gcc -c -fsyntax-only %FLAGS %FILE
SCl0
SCC0GNU
SCm0/usr/include
SCn0__STDC__ 1\012__GNUC__ 2\012_GRASP_IGNORE
SCo0Parse Local Only
SCp0%MAIN_PATH%SEP%A %ARGS
SCr0%MAIN_PATH
SCD0
SCw0gdb %CD --args %A %ARGS
SCy0%MAIN_PATH
SCF0f1-\\s*at\\s*(\\S(?:\\s*\\S)*):(\\d+)
SC10
SC40
SC50
SCI0
SCJ0
SCS0
SCU0PATH+=C:\\mingw\\bin;\012PATH+=%<JGRASP_PATHS>%;
BCa01
BCe01
BCc01
C40
SC50
SCI0
SCJ0
SCS0
SCU0PATH+=C:\\mingw\\bin;\012PATH+=%<JGRASP_PATHS>%;
BCa01
BCe01
BCc01
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     SCe0make %FLAGS
SCi0
SCz0GNU
SCf0gcc %D -g %D %FLAGS %FILE
SCj0
SCA0GNU
SCg0gcc %D -g %D -c %FLAGS %FILE
SCk0
SCB0GNU
SCh0gcc -c -fsyntax-only %FLAGS %FILE
SCl0
SCC0GNU
SCm0/usr/include
SCn0__STDC__ 1\012__GNUC__ 2\012_GRASP_IGNORE
SCo0Parse Local Only
SCp0%MAIN_PATH%SEP%A %CY %ARGS
SCr0%MAIN_PATH
SCD0
SCw0insight --args %A %ARGS
SCy0%MAIN_PATH
SCF0f1-\\s*at\\s*(\\S(?:\\s*\\S)*):(\\d+)
SC10
SC40
SC50
SCI0
SCJ0
SCS0
SCU0PATH+=%<JGRASP_PATHS>%;\012CYGWIN=nodosfilewarning
BCa01
BCe01
BCc01
0
SC50
SCI0
SCJ0
SCS0
SCU0PATH+=%<JGRASP_PATHS>%;\012CYGWIN=nodosfilewarning
BCa01
BCe01
BCc01
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     SCe0make %FLAGS
SCi0
SCz0GNU
SCf0gcc %D -g %D %FLAGS %FILE
SCj0
SCA0GNU
SCg0gcc %D -g %D -c %FLAGS %FILE
SCk0
SCB0GNU
SCh0gcc -c -fsyntax-only %FLAGS %FILE
SCl0
SCC0GNU
SCm0/usr/include
SCn0__STDC__ 1\012__GNUC__ 2\012_GRASP_IGNORE
SCo0Parse Local Only
SCp0%MAIN_PATH%SEP%A %CY %ARGS
SCr0%MAIN_PATH
SCD0
SCw0insight --args %A %ARGS
SCy0%MAIN_PATH
SCF0f1-\\s*at\\s*(\\S(?:\\s*\\S)*):(\\d+)
SC10
SC40
SC50
SCI0
SCJ0
SCS0
SCU0PATH+=%<JGRASP_PATHS>%;\012PATH+=C:\\cygwin\\bin;\012CYGWIN=nodosfilewarning
BCa01
BCe01
BCc01
PATH+=C:\\cygwin\\bin;\012PATH+=%<JGRASP_PATHS>%;\012CYGWIN=nodosfilewarning
BCa01
BCe01
BCc01
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               SCe0NMAKE
SCi0
SCz0f1-(\\S(?:\\s*\\S)*)\\((\\d+)\\).*
SCf0CL  %N /MT %N  %D /Zi /MTd %D  %FLAGS %FILE
SCj0
SCA0f1-(\\S(?:\\s*\\S)*)\\((\\d+)\\).*
SCg0Cl /c  %D /Zi %D  %FLAGS %FILE
SCk0
SCB0f1-(\\S(?:\\s*\\S)*)\\((\\d+)\\).*
SCh0Cl /Zs %FLAGS %FILE
SCl0
SCC0f1-(\\S(?:\\s*\\S)*)\\((\\d+)\\).*
SCm0/usr/include
SCn0__STDC__ 1\012_GRASP_IGNORE
SCo0Parse Local Only
SCp0%MAIN_BASE.exe %ARGS
SCr0%MAIN_PATH
SCD0
SCw0
SCy0%MAIN_PATH
SCF0
SC10
SC40
SC50
SCI0C:\\Program Files\\Microsoft Visual Studio 8\\VC
SCJ0C:\\Program Files\\Microsoft Visual Studio 8\\Common7\\IDE
SCS0
SCU0INCLUDE+=%V1\\include;\012LIB=%V1\\lib\012PATH+=%V1\\bin;%V2;\012PATH+=%<JGRASP_PATHS>%;
BCa01
BCe00
BCc01
c01
