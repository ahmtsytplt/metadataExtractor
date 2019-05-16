# metadataExtractor
A program to read metadata of media files as JPG, WAV and GIF.

If this is your homework, please do not copy/pasta. Try to learn its main function. It is easy.

The main function is placed in Try-Catch block beginning at 83rd row in the code. The other parts are UI elements. You can directyly skip to there.

The algorithm:
- Read the file name and understand file type.
- Read every line in file and put it in a String variable with BufferedReader and StringBuilder.
- Read characters in specific locations depending on file type.
- Convert the value of character to hex value.
- Apply Little Endian / Big Endian format and calculate its value.
- Represent with console output.

Shown Metadata:

-GIF:
  Image Resolution, Background Colour.
  
-WAV:
  Chunk Size, Format, Channel No, Sampling Rate, Bits per Sample.
  
-JPG:
  File Size, Big Endian/ Little Endian, Processed or Original.
  
