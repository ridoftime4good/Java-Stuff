SAe0
SAi0
SAz0
SAf0gnatmake %D -g %D %FLAGS %file
SAj0
SAA0f1-(\\S(?:\\s*\\S)*?):(\\d+):.*
SAg0gcc -c %D -g %D %FLAGS %file
SAk0
SAB0f1-(\\S(?:\\s*\\S)*?):(\\d+):.*
SAh0gcc -c -gnatc %FLAGS %file
SAl0
SAC0f1-(\\S(?:\\s*\\S)*?):(\\d+):.*
SAp0%MAIN_PATH%SEP%main_base %ARGS
SAr0%MAIN_PATH
SAD0
SAw0gdb --args %main_base %ARGS
SAy0%MAIN_PATH
SAF0
SA10
SA40
SA50
SAI0
SAJ0
SAS0
SAU0PATH+=%<JGRASP_PATHS>%;
                                                                                                                  p�e =   �a�A�a͈        
   4�^     $�e =   �a�A�a�A�  �           `�^     ��e =   �a�A�a�A�!��a��!�A�  �  SAe0
SAi0
SAz0
SAf0gnatmake %D -g %D %FLAGS %file
SAj0
SAA0f1-(\\S(?:\\s*\\S)*?):(\\d+):.*
SAg0gcc -c %D -g %D %FLAGS %file
SAk0
SAB0f1-(\\S(?:\\s*\\S)*?):(\\d+):.*
SAh0gcc -c -gnatc %FLAGS %file
SAl0
SAC0f1-(\\S(?:\\s*\\S)*?):(\\d+):.*
SAp0%MAIN_PATH%SEP%main_base %ARGS
SAr0%MAIN_PATH
SAD0
SAw0gdb --args %main_base %ARGS
SAy0%MAIN_PATH
SAF0
SA10
SA40
SA50
SAI0
SAJ0
SAS0
SAU0PATH+=%<JGRASP_PATHS>%;\012PATH+=C:\\GNAT\\bin;
!�u            H�^     Lf +   A�!�{             l�^     pf +   A�!ʜ             ��^     �f /   A�!�a�  �            ��^     @f /   A�!�a�  �             �^     Tf +   A�!�a�  �             �^     x	f +   A�!�a�  �            0�^     f +   A�!�a�  �  SAe0
SAi0
SAz0
SAf0gnatmake %D -g %D %FLAGS %file
SAj0
SAA0f1-(\\S(?:\\s*\\S)*?):(\\d+):.*
SAg0gcc -c %D -g %D %FLAGS %file
SAk0
SAB0f1-(\\S(?:\\s*\\S)*?):(\\d+):.*
SAh0gcc -c -gnatc %FLAGS %file
SAl0
SAC0f1-(\\S(?:\\s*\\S)*?):(\\d+):.*
SAp0%MAIN_PATH%SEP%main_base %ARGS
SAr0%MAIN_PATH
SAD0
SAw0gdb --args %main_base %ARGS
SAy0%MAIN_PATH
SAF0
SA10
SA40
SA50
SAI0
SAJ0
SAS0
SAU0PATH+=%<JGRASP_PATHS>%;\012PATH+=C:\\GNAT\\bin;
                                                                                                                                                                                                                                                                                                                            SAe0
SAi0
SAz0
SAf0bash -c "gnatmake %D -g %D %<file>"
SAj0
SAA0f1-(\\S(?:\\s*\\S)*?):(\\d+):.*
SAg0gcc -c %D -g %D %FLAGS %file
SAk0
SAB0f1-(\\S(?:\\s*\\S)*?):(\\d+):.*
SAh0gcc -c -gnatc %FLAGS %file
SAl0
SAC0f1-(\\S(?:\\s*\\S)*?):(\\d+):.*
SAp0%MAIN_PATH%SEP%main_base %ARGS
SAr0%MAIN_PATH
SAD0
SAw0gdb --args %main_base %ARGS
SAy0%MAIN_PATH
SAF0
SA10
SA40
SA50
SAI0
SAJ0
SAS0This runs gnatmake through bash, which is necessary on\012Cygwin 1.5 and higher. It does not support flags. On other\012systems, you can switch to the "gnat - generic" environment\012to avoid the extra overhead and gain flags capability.
SAU0PATH+=%<JGRASP_PATHS>%;
                                                                                                                                                                      SAe0
SAi0
SAz0
SAf0bash -c "gnatmake %D -g %D %<FLAGS> %<file>"
SAj0
SAA0f1-(\\S(?:\\s*\\S)*?):(\\d+):.*
SAg0gcc -c %D -g %D %FLAGS %file
SAk0
SAB0f1-(\\S(?:\\s*\\S)*?):(\\d+):.*
SAh0gcc -c -gnatc %FLAGS %file
SAl0
SAC0f1-(\\S(?:\\s*\\S)*?):(\\d+):.*
SAp0%MAIN_PATH%SEP%main_base %ARGS
SAr0%MAIN_PATH
SAD0
SAw0gdb %main_base %ARGS
SAy0%MAIN_PATH
SAF0
SA10
SA40
SA50
SAI0
SAJ0
