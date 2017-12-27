'''
Version 4
Fixes: none
Updates:
1.Added textbox and button
2.Message boxes added and working
3.Credits work (appear in new GUI)
'''

#imports libraries needed for project
from tkinter import *
import pygame


def main():
    #class that creates the GUI
    class GUI:
        #initialize the GUI window
        main = Tk()
        #sets window size
        main.geometry('400x400')
        #sets window title
        main.title('Ocarina Simulator')
        #puts text in the middle of the screen
        myLabel = Label(text="The Legend of Zelda").pack()
        myLabel2 = Label(text="Ocarina Simulator").pack()
        myLabel3 = Label(text="Press the Q,W,E,R,T,Y,U, & 1-5 Keys to Play").pack()
        myLabel4 = Label(text="Do you like this program? (Enter yes, no, or credits)")
        myLabel4.place(x=85, y=100)
        #holds string variable from myTextBox
        yesNoVar = StringVar()
        #Entry box, textvariable stores input into yesNoVar
        mtTextBox = Entry(textvariable = yesNoVar).place(x=135, y=125)
        def textInput():
            #gets yesNoVar and compares it with options listed below
            if GUI.yesNoVar.get().lower() == "yes":
                #creates message box showing correct!
                messagebox.showwarning( "Thats Right!", "You picked the right answer!")
                #This prevents the program from staying in the entry box(otherwise you can't play sound when you click in text box with curser)
                frame.focus()
            elif GUI.yesNoVar.get().lower() == "no":
                i = 5
                while i > 0:
                    messagebox.showerror("Wrong!", "You have to answer with yes!"+"\n                 "+str(i))
                    i = i - 1
                frame.focus()
            #creates new window and displays the credits
            elif GUI.yesNoVar.get().lower() == "credits":
                def programmer():
                    name = "Matthew Vitt"
                    return name
                def designer1():
                    name = "James Snell"
                    return name
                def designer2():
                    name = "Nathan Rivera-Melo"
                    return name
                def analyst():
                    name = "Alec Pederson"
                    return name
                prog = programmer()
                des1 = designer1()
                des2 = designer2()
                ana = analyst()
                creditGUI = Tk()
                creditGUI.geometry("400x400")
                credits1 = Label(creditGUI, text = "Analyst..........................."+ana).place(x=0,y=0)
                credits2 = Label(creditGUI, text = "Designer........................."+des1).place(x=0,y=20)
                credits3 = Label(creditGUI, text = "Designer........................."+des2).place(x=0,y=40)
                credits4 = Label(creditGUI, text = "Programmer.................."+prog).place(x=0,y=60)
                credits5 = Label(creditGUI, text = "All sounds and names belongs to nintendo.\nThis program was built as a class project.").place(x=0, y=80)
                frame.focus()
            else:
                messagebox.showerror("Um...", "lets follow the instructions...")
                frame.focus()
        #Creates button that call textInput module
        myButton = Button(text="OK", command = textInput).place(x=180, y=150)
        



    #class that makes the noises
    class Ocarina:
        #event works with frame at bottom of code, when key is pressed(Event takes place) sound is played
        def callMe(event):
            #sets background color to white
            GUI.main.configure(background = 'white')
            #initialize the sound
            pygame.mixer.init(frequency=22050, size=-16, channels=2, buffer=4096)
            #loads sound into program
            pygame.mixer.music.load("abutton.wav")
            #plays the sound
            pygame.mixer.music.play()

        def callMe2(event):
            GUI.main.configure(background = 'green')
            pygame.mixer.init(frequency=22050, size=-16, channels=2, buffer=4096)
            pygame.mixer.music.load("leftbutton.wav")
            pygame.mixer.music.play()

        def callMe3(event):
            GUI.main.configure(background = 'black')
            pygame.mixer.init(frequency=22050, size=-16, channels=2, buffer=4096)
            pygame.mixer.music.load("OOTError.wav")
            pygame.mixer.music.play()
        def callMe4(event):
            GUI.main.configure(background = 'blue')
            pygame.mixer.init(frequency=22050, size=-16, channels=2, buffer=4096)
            pygame.mixer.music.load("upbutton.wav")
            pygame.mixer.music.play()
        def callMe5(event):
            GUI.main.configure(background = 'red')
            pygame.mixer.init(frequency=32000, size=-16, channels=2, buffer=8000)
            pygame.mixer.music.load("OOTCorrect.wav")
            pygame.mixer.music.play()
        def callMe6(event):
            GUI.main.configure(background = 'yellow')
            pygame.mixer.init(frequency=22050, size=-16, channels=2, buffer=4096)
            pygame.mixer.music.load("rightbutton.wav")
            pygame.mixer.music.play()
        def callMe7(event):
            GUI.main.configure(background = 'orange')
            pygame.mixer.init(frequency=22050, size=-16, channels=2, buffer=4096)
            pygame.mixer.music.load("downbutton.wav")
            pygame.mixer.music.play()
        def songOfStorm(event):
            pygame.mixer.init(frequency=22050, size=-16, channels=2, buffer=4096)
            pygame.mixer.music.load("SongOfStorms.mid")
            pygame.mixer.music.play()
        def zeldasLullaby(event):
            pygame.mixer.init(frequency=22050, size=-16, channels=2, buffer=4096)
            pygame.mixer.music.load("ZeldasLullaby.mid")
            pygame.mixer.music.play()
        def minuetOfForest(event):
            pygame.mixer.init(frequency=22050, size=-16, channels=2, buffer=4096)
            pygame.mixer.music.load("MinuetOfForest.mid")
            pygame.mixer.music.play()
        def eponasSong(event):
            pygame.mixer.init(frequency=22050, size=-16, channels=2, buffer=4096)
            pygame.mixer.music.load("epona_song.mid")
            pygame.mixer.music.play()
        def sariasSong(event):
            pygame.mixer.init(frequency=22050, size=-16, channels=2, buffer=4096)
            pygame.mixer.music.load("saria_song.mid")
            pygame.mixer.music.play()
        


    #init Frame, gets ready for event(ex. key being pressed)
    frame = Frame()
    #binds the 'q' key to Ocarina.callMe....and so on
    frame.bind('<q>', Ocarina.callMe)
    frame.bind('<w>', Ocarina.callMe2)
    frame.bind('<u>', Ocarina.callMe3)
    frame.bind('<e>', Ocarina.callMe4)
    frame.bind('<y>', Ocarina.callMe5)
    frame.bind('<r>', Ocarina.callMe6)
    frame.bind('<t>', Ocarina.callMe7)
    frame.bind('1', Ocarina.songOfStorm)
    frame.bind('2', Ocarina.zeldasLullaby)
    frame.bind('3', Ocarina.eponasSong)
    frame.bind('4', Ocarina.sariasSong)
    frame.bind('5', Ocarina.minuetOfForest)

    #if you don't have this frame events won't work
    frame.focus_set()
    #won't work without .pack() for some reason
    frame.pack()
    #helps with memory usage
    GUI.main.mainloop()
    

main()
