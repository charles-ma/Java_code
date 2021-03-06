package sequences;

import java.awt.BorderLayout;

import java.lang.Integer;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Visually displays the effect of operations on a stack, a queue, and a deque.
 * @author David Matuszek
 * @version January 2012
 */
public class StackQueueDequeDisplay extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextArea stackContents;
    private JTextArea queueContents;
    private JTextArea dequeContents;
    private JTextField stackInOut, queueInOut, dequeInOut;
    private Stack<String> theStack;
    private Queue<String> theQueue;
    private Deque<String> theDeque;
    private ButtonListener buttonListener = new ButtonListener();
    private JButton stackGetSize, stackClear, stackPush, stackPop, stackPeek,
                    stackIterate, stackHasNext, stackNext;
    private JButton queueGetSize, queueClear, queueEnqueue, queueDequeue, queuePeek,
                    queueIterate, queueHasNext, queueNext;
    private JButton dequeGetSize, dequeClear, dequeAddToFront, dequeAddToRear,
                    dequeRemoveFromFront, dequeRemoveFromRear, dequePeekAtFront,
                    dequePeekAtRear, dequeIterateFromFront, dequeIterateFromRear,
                    dequeHasNext, dequeNext;
    /**
     * Runs the program.
     * @param args Unused.
     */
    public static void main(String[] args) {
        StackQueueDequeDisplay display = new StackQueueDequeDisplay();
        display.theStack = new Stack<String>();
        display.theQueue = new Queue<String>();
        display.theDeque = new Deque<String>();
        display.createGui();
    }

    /**
     * Creates the GUI.
     */
    @SuppressWarnings("synthetic-access")
    private void createGui() {
        setTitle("Please replace this string with your name");
        stackContents = new JTextArea(10, 18);
        queueContents = new JTextArea(10, 18);
        dequeContents = new JTextArea(10, 18);

        stackInOut = new JTextField();
        queueInOut = new JTextField();
        dequeInOut = new JTextField();
        
        Container c = getContentPane();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel labelPanel = new JPanel();
        JPanel contentPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        
        JPanel stackButtonPanel = new JPanel();
        JPanel queueButtonPanel = new JPanel();
        JPanel dequeButtonPanel = new JPanel();
        
        JPanel stackDataPanel = new JPanel();
        JPanel queueDataPanel = new JPanel();
        JPanel dequeDataPanel = new JPanel();

        c.setLayout(new BorderLayout());
        c.add(labelPanel, BorderLayout.NORTH);
        c.add(contentPanel, BorderLayout.CENTER);
        c.add(buttonPanel, BorderLayout.SOUTH);
        
        labelPanel.setLayout(new GridLayout(1, 3));
        labelPanel.add(new JLabel("Stack (top)"));
        labelPanel.add(new JLabel("Queue (front)"));
        labelPanel.add(new JLabel("Deque (front)"));
        
        contentPanel.setLayout(new GridLayout(1, 3));
        contentPanel.add(new JScrollPane(stackContents));
        contentPanel.add(new JScrollPane(queueContents));
        contentPanel.add(new JScrollPane(dequeContents));
        
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(stackButtonPanel);
        buttonPanel.add(queueButtonPanel);
        buttonPanel.add(dequeButtonPanel);
        
        stackDataPanel.setLayout(new BorderLayout());
        stackDataPanel.add(new JLabel("Data:"), BorderLayout.WEST);
        stackDataPanel.add(stackInOut, BorderLayout.CENTER);
        
        buttonListener = new ButtonListener();
        
        stackButtonPanel.setLayout(new GridLayout(13, 1));
        stackButtonPanel.add(stackDataPanel);
        stackGetSize = addButton("Get Size", stackButtonPanel);
        stackClear = addButton("Clear", stackButtonPanel);
        stackPush = addButton("Push", stackButtonPanel);
        stackButtonPanel.add(new JLabel(""));
        stackPop = addButton("Pop", stackButtonPanel);
        stackButtonPanel.add(new JLabel(""));
        stackPeek = addButton("Peek", stackButtonPanel);
        stackButtonPanel.add(new JLabel(""));
        stackIterate = addButton("Iterate", stackButtonPanel);
        stackButtonPanel.add(new JLabel(""));
        stackHasNext = addButton("Has Next", stackButtonPanel);
        stackNext = addButton("Next", stackButtonPanel);
        
        queueDataPanel.setLayout(new BorderLayout());
        queueDataPanel.add(new JLabel("Data:"), BorderLayout.WEST);
        queueDataPanel.add(queueInOut, BorderLayout.CENTER);
        
        queueButtonPanel.setLayout(new GridLayout(13, 1));
        queueButtonPanel.add(queueDataPanel);
        queueGetSize = addButton("Get Size", queueButtonPanel);
        queueClear = addButton("Clear", queueButtonPanel);
        queueEnqueue = addButton("Enqueue", queueButtonPanel);
        queueButtonPanel.add(new JLabel(""));
        queueDequeue = addButton("Dequeue", queueButtonPanel);
        queueButtonPanel.add(new JLabel(""));
        queuePeek = addButton("Peek", queueButtonPanel);
        queueButtonPanel.add(new JLabel(""));
        queueIterate = addButton("Iterate", queueButtonPanel);
        queueButtonPanel.add(new JLabel(""));
        queueHasNext = addButton("Has Next", queueButtonPanel);
        queueNext = addButton("Next", queueButtonPanel);
        
        dequeDataPanel.setLayout(new BorderLayout());
        dequeDataPanel.add(new JLabel("Data:"), BorderLayout.WEST);
        dequeDataPanel.add(dequeInOut, BorderLayout.CENTER);
        
        dequeButtonPanel.setLayout(new GridLayout(13, 1));
        dequeButtonPanel.add(dequeDataPanel);
        dequeGetSize = addButton("Get Size", dequeButtonPanel);
        dequeClear = addButton("Clear", dequeButtonPanel);
        dequeAddToFront = addButton("AddToFront", dequeButtonPanel);
        dequeAddToRear = addButton("AddToRear", dequeButtonPanel);
        dequeRemoveFromFront = addButton("Remove From Front", dequeButtonPanel);
        dequeRemoveFromRear = addButton("Remove From Rear", dequeButtonPanel);
        dequePeekAtFront = addButton("Peek At Front", dequeButtonPanel);
        dequePeekAtRear = addButton("Peek At Rear", dequeButtonPanel);
        dequeIterateFromFront = addButton("Iterate From Front", dequeButtonPanel);
        dequeIterateFromRear = addButton("Iterate From Rear", dequeButtonPanel);
        dequeHasNext = addButton("Has Next", dequeButtonPanel);
        dequeNext = addButton("Next", dequeButtonPanel);
        
        pack();
        setVisible(true);
    }

    /**
     * Creates a button, adds it to the panel, and attaches the global
     * listener <code>buttonListener</code> to it.
     * @param label The text to put on the button.
     * @param panel The panel to which the button should be added.
     * @return The button.
     */
    private JButton addButton(String label, JPanel panel) {
        JButton button = new JButton(label);
        panel.add(button);
        button.addActionListener(buttonListener);
        return button;
    }

    /**
     * Update the contents to show the stack
     */
    private void updateStack() {
        stackContents.setText("");
        Iterator<String> it = theStack.iterator();
        while(it.hasNext()) stackContents.append(it.next() + "\n");
    }
    
    /**
     * Update the contents to show the queue
     */
    private void updateQueue() {
        queueContents.setText("");
        Iterator<String> it = theQueue.iterator();
        while(it.hasNext()) queueContents.append(it.next() + "\n");
    }
    
    /**
     * Update the contents to show the deque
     */
    private void updateDeque() {
        dequeContents.setText("");
        Iterator<String> it = theDeque.iterator();
        while(it.hasNext()) dequeContents.append(it.next() + "\n");
    }
    
    private class ButtonListener implements ActionListener {

        Iterator<String> i = null;
        Iterator<String> iq = null;
        Iterator<String> id = null;
        @SuppressWarnings("synthetic-access")
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource().equals(stackGetSize)) {
                stackInOut.setText(theStack.size() + "");
            } else if (event.getSource().equals(stackClear)) {
                // Call the appropriate method and update the display
                theStack.clear();
                updateStack();
                //i = theStack.iterator();
            } else if (event.getSource().equals(stackPush)) {
                // Call the appropriate method and update the display
                theStack.add(stackInOut.getText());
                updateStack();
                //i = theStack.iterator();
            } else if (event.getSource().equals(stackPop)) {
                // Call the appropriate method and update the display
                stackInOut.setText(theStack.remove());
                updateStack();
                //i = theStack.iterator();
            } else if (event.getSource().equals(stackPeek)) {
                // Call the appropriate method and update the display
                stackInOut.setText(theStack.peek());
                updateStack();
            } else if (event.getSource().equals(stackIterate)) {
                // Call the appropriate method and update the display
                i = theStack.iterator();
                stackInOut.setText("iteration starts!");
                updateStack();
            } else if (event.getSource().equals(stackHasNext)) {
                // Call the appropriate method and update the display
                if(i == null) i = theStack.iterator();
                if(i.hasNext()) stackInOut.setText("true");
                else stackInOut.setText("false");
            } else if (event.getSource().equals(stackNext)) {
                // Call the appropriate method and update the display
                if(i == null) i = theStack.iterator();
                stackInOut.setText(i.next());
            }
            else if (event.getSource().equals(queueGetSize)) {
                // Call the appropriate method and update the display
                queueInOut.setText(theQueue.size() + "");
            } else if (event.getSource().equals(queueClear)) {
                // Call the appropriate method and update the display
                theQueue.clear();
                updateQueue();
                //iq = theQueue.iterator();
            } else if (event.getSource().equals(queueEnqueue)) {
                // Call the appropriate method and update the display
                theQueue.add(queueInOut.getText());
                updateQueue();
                //iq = theQueue.iterator();
            } else if (event.getSource().equals(queueDequeue)) {
                // Call the appropriate method and update the display
                theQueue.remove();
                updateQueue();
                //iq = theQueue.iterator();
            } else if (event.getSource().equals(queuePeek)) {
                // Call the appropriate method and update the display
                queueInOut.setText(theQueue.peek());
                updateQueue();
            } else if (event.getSource().equals(queueIterate)) {
                // Call the appropriate method and update the display
                queueInOut.setText("iteration starts!");
                updateQueue();
                iq = theQueue.iterator();
            } else if (event.getSource().equals(queueHasNext)) {
                // Call the appropriate method and update the display
                if(iq == null) iq = theQueue.iterator();
                if(iq.hasNext()) queueInOut.setText("true");
                else queueInOut.setText("false");
            } else if (event.getSource().equals(queueNext)) {
                // Call the appropriate method and update the display
                if(iq == null) iq = theQueue.iterator();
                queueInOut.setText(iq.next());
            }
            else if (event.getSource().equals(dequeGetSize)) {
                // Call the appropriate method and update the display
                dequeInOut.setText(theDeque.size() + "");
            } else if (event.getSource().equals(dequeClear)) {
                // Call the appropriate method and update the display
                theDeque.clear();
                updateDeque();
                //id = theDeque.iterator();
            } else if (event.getSource().equals(dequeAddToFront)) {
                // Call the appropriate method and update the display
                theDeque.addFront(dequeInOut.getText());
                updateDeque();
                //id = theDeque.iterator();
            } else if (event.getSource().equals(dequeAddToRear)) {
                // Call the appropriate method and update the display
                theDeque.add(dequeInOut.getText());
                updateDeque();
                //id = theDeque.iterator();
            } else if (event.getSource().equals(dequeRemoveFromFront)) {
                // Call the appropriate method and update the display
                theDeque.remove();
                updateDeque();
                //id = theDeque.iterator();
            } else if (event.getSource().equals(dequeRemoveFromRear)) {
                // Call the appropriate method and update the display
                theDeque.removeRear();
                updateDeque();
                //id = theDeque.iterator();
            } else if (event.getSource().equals(dequePeekAtFront)) {
                // Call the appropriate method and update the display
                dequeInOut.setText(theDeque.peek());
                updateDeque();
            } else if (event.getSource().equals(dequePeekAtRear)) {
                // Call the appropriate method and update the display
                dequeInOut.setText(theDeque.peekRear());
                updateDeque();
            } else if (event.getSource().equals(dequeIterateFromFront)) {
                // Call the appropriate method and update the display
                dequeInOut.setText("iterator from the front!");
                updateDeque();
                id = theDeque.iterator();
            } else if (event.getSource().equals(dequeIterateFromRear)) {
                // Call the appropriate method and update the display
                dequeInOut.setText("iterator from the rear!");
                updateDeque();
                id = theDeque.reverseIterator();
            } else if (event.getSource().equals(dequeHasNext)) {
                // Call the appropriate method and update the display
                if(id == null) id = theDeque.iterator();
                if(id.hasNext()) dequeInOut.setText("true");
                else dequeInOut.setText("false");
            } else if (event.getSource().equals(dequeNext)) {
                // Call the appropriate method and update the display
                if(id == null) id = theDeque.iterator();
                dequeInOut.setText(id.next());
            } else {
                // Call the appropriate method and update the display
            }
        }
    }
}
