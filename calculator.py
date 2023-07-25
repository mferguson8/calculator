import tkinter as tk

def on_click(btn_text):
    if btn_text == "=":
        try:
            result = eval(entry.get())
            entry.delete(0, tk.END)
            entry.insert(tk.END, str(result))
        except Exception:
            entry.delete(0, tk.END)
            entry.insert(tk.END, "Error")
    elif btn_text == "C":
        entry.delete(0, tk.END)
    else:
        entry.insert(tk.END, btn_text)

# Create the main application window
root = tk.Tk()
root.title("Calculator")

# Create the entry widget
entry = tk.Entry(root, width=25, font=("Arial", 20), borderwidth=5)
entry.grid(row=0, column=0, columnspan=4, padx=10, pady=10)

# Define the buttons
buttons = [
    ("7", 1, 0), ("8", 1, 1), ("9", 1, 2), ("/", 1, 3),
    ("4", 2, 0), ("5", 2, 1), ("6", 2, 2), ("*", 2, 3),
    ("1", 3, 0), ("2", 3, 1), ("3", 3, 2), ("-", 3, 3),
    ("0", 4, 0), (".", 4, 1), ("=", 4, 2), ("+", 4, 3),
    ("C", 5, 0),
]

# Add the buttons to the window
for btn_text, row, col in buttons:
    button = tk.Button(root, text=btn_text, width=5, height=2, font=("Arial", 16),
                       command=lambda text=btn_text: on_click(text))
    button.grid(row=row, column=col, padx=5, pady=5)

# Start the main event loop
root.mainloop()
