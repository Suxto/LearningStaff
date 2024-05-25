import numpy as np
import matplotlib.pyplot as plt

x = np.linspace(0, 2 * np.pi, 200)
y1 = np.sin(x)
y2 = np.cos(x)

plt.plot(x, y1, label='sin')
plt.plot(x, y2, label='cos')

plt.annotate(xy=(np.pi * 2 / 3, np.sin(np.pi * 2 / 3)), text=str(np.sin(2 * np.pi * 2 / 3)), xytext=(2, 0.5),
             weight='bold', color='green', arrowprops=dict(arrowstyle='-|>', connectionstyle='arc3', color='red'),
             bbox=dict(boxstyle='round,pad=0.5', fc='yellow', ec='k', lw=1, alpha=0.4))
plt.annotate(xy=(np.pi * 2 / 3, np.cos(np.pi * 2 / 3)), text=str(np.cos(2 * np.pi * 2 / 3)), xytext=(2, -1.0),
             weight='bold', color='green', arrowprops=dict(arrowstyle='-|>', connectionstyle='arc3', color='red'),
             bbox=dict(boxstyle='round,pad=0.5', fc='yellow', ec='k', lw=1, alpha=0.4))
plt.title("sin & cos")
plt.legend(loc='upper right')
plt.show()
