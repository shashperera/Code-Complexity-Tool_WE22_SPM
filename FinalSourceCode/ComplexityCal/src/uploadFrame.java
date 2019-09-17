import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Color;

public class uploadFrame extends JFrame {

	private JPanel contentPane;
	private JTextField fileText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					uploadFrame frame = new uploadFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public uploadFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 633, 461);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		fileText = new JTextField();
		fileText.setBounds(170, 179, 267, 19);
		contentPane.add(fileText);
		fileText.setColumns(10);

		JButton browseButton = new JButton("Browse");
		browseButton.addActionListener(new ActionListener() {
			File file;

			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				int approveOption = jfc.showOpenDialog(null);

				if (approveOption == JFileChooser.APPROVE_OPTION) {
					file = jfc.getSelectedFile();
					fileText.setText(file.getAbsolutePath());
					System.out.println(file);

				}
			}
		});
		browseButton.setBounds(447, 178, 85, 21);
		contentPane.add(browseButton);

		JButton exportReportButton = new JButton("Export Report");
		exportReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Variables for calculating ctc
				int CtcIfComplexity = 0;
				int CtcLoopComplexity = 0;
				int CtcSwitchComplexity = 0;
				
				// Variables for Complexity due to Size (Cs)
				int Cs=0;
				
				//Variables for calculating Cnc
				int total_complexity = 0;
		        int line_complexity = 0;
		        int line_count = 0;
		        int line_no = 0;
		        
		        int totalCs = 0;
		        int totalCtc = 0;
		        int totalCnc = 0;
		        int totalCi = 0;

				CtcComplexityCalculator obj = new CtcComplexityCalculator();
				SizeComplexityCal objCs = new SizeComplexityCal();
				CiComplexityCalculator objCi = new CiComplexityCalculator();

				BufferedReader reader;
				try {
					reader = new BufferedReader(new FileReader(fileText.getText()));
					String line;
					String complexity;
					String CurrentLine="";

					FileWriter writer = new FileWriter("C:\\Users\\" + System.getProperty("user.name") + "\\Downloads\\result.csv");
					BufferedWriter bw = new BufferedWriter(writer);

					bw.write("Source Code, CS, CTC, CNC, Ci, CP\n");
					
					//Calling inheritance method
					int arr[] = objCi.inheritanceComplexity(fileText.getText());

					while ((line = reader.readLine()) != null) {
						CtcIfComplexity = 0;
						CtcLoopComplexity = 0;
						CtcSwitchComplexity = 0;
						CurrentLine=line;
						int lineNo = 0; 
						bw.write(line + ",");
						
						
						// ----------------Caluculating Cs-----------------------------
						
						Cs = objCs.measureSize(line).size();
						totalCs = totalCs + Cs;
						bw.write(Cs + ",");
						//-------------------------------------------------------------

						// ----------------Caluculating Ctc---------------------------
						if (line.contains("if")) {
							CtcIfComplexity = CtcIfComplexity + obj.FindIf(line);
							complexity = Integer.toString(CtcIfComplexity);
							bw.write(complexity);
							totalCtc = totalCtc + CtcIfComplexity;

						}

						if (line.contains("while") || line.contains("for")) {
							CtcLoopComplexity = CtcLoopComplexity + obj.FindLoops(line);
							complexity = Integer.toString(CtcLoopComplexity);
							bw.write(complexity);
							totalCtc = totalCtc + CtcLoopComplexity;
						}

						if ((line.contains("switch") && line.contains("{")) || line.contains("switch")) {
							bw.write("0");
							while (((line = reader.readLine()) != null)) {
								if (line.contains("}")) {
									break;
								}
								CtcSwitchComplexity = CtcSwitchComplexity + obj.FindSwitch(line);
								complexity = Integer.toString(CtcSwitchComplexity);
								totalCtc = totalCtc + CtcSwitchComplexity;
								Cs = objCs.measureSize(line).size();
								bw.write("\n" + line +"," + Cs +","+ complexity + ",0,0");
								CtcSwitchComplexity = 0;
							}
							bw.write("\n");
							CtcSwitchComplexity = 1;
						}

						if (CtcIfComplexity == 0 && CtcLoopComplexity == 0 && CtcSwitchComplexity == 0) {
							bw.write("0");
						}
						//-----------------------------------------------------------------------------------------------
						
						//-----------------------------------Calculatiing Cnc---------------------------------------------

						if (((line.contains("if (") || line.contains("if(")) && (line.contains(") { ") || line.contains("){ "))) || line.contains("if(") || line.contains("if (") || ((line.contains("for (") || line.contains("for(")) && (line.contains(") { ") || line.contains("){ "))) || line.contains("for (") || line.contains("for(") || ((line.contains("while (") || line.contains("while(")) && (line.contains(") { ") || line.contains("){ "))) || line.contains("while(") || line.contains("while (")) {
                            total_complexity++;
                            line_complexity = line_complexity + 1;
                            bw.write("," + line_complexity);
                            //System.out.println("line cnc:" + line_complexity);
                            //System.out.println(line);
                            line_complexity = 0;

                            while (((line = reader.readLine()) != null)) {
                                if (line.contains("} ")) {
                                    bw.write(line_complexity);
                                    //System.out.println("line cnc:" + line_complexity);
                                    break;
                                } else if ((line.contains("if (") || line.contains("if(")) || line.contains("for(") || line.contains("while(")) {
                                    total_complexity++;
                                    line_complexity = line_complexity + 2;
                                    bw.write(line_complexity);                           
                                    line_complexity = 0;
                                    
                                } else if (line.contains("return ")) {
                                    total_complexity++;
                                     bw.write("\n");
                                    line_complexity = line_complexity + 1;
                                    bw.write(line + ",");
                                    Cs = objCs.measureSize(line).size();
                                    bw.write(Cs +",0,"+line_complexity);
                                    //System.out.println("line cnc:" + line_complexity);
                                    line_complexity = 0;
                                    break;
                                } else if ((line.contains("for(") && line.contains("{")) || line.contains("for (")) {
                                      bw.write("\n");
                                    total_complexity++;
                                    line_complexity = line_complexity + 1;
                                    bw.write(line + ",");                                  
                                    Cs = objCs.measureSize(line).size();
                                    bw.write(Cs +",0,"+line_complexity);
                                    //System.out.println("line cnc:" + line_complexity);
                                    line_complexity = 0;
                                    break;

                                } else if (line.contains("System.out.")) {
                                    bw.write("\n");
                                    total_complexity++;
                                    line_complexity = line_complexity + 1;
                                    bw.write(line + ",");
                                    
                                    Cs = objCs.measureSize(line).size();
                                    bw.write(Cs +",0,"+line_complexity);
                                    //System.out.println("line cnc:" + line_complexity);
                                    line_complexity = 0;
                                    break;
                                } else if ((line.contains("while(") && line.contains("{")) || line.contains("while (")) {
                                    bw.write("\n");
                                    total_complexity++;
                                    line_complexity = line_complexity + 1;
                                    bw.write(line + ",");
                                    
                                    bw.write(line_complexity);
                                    //System.out.println("line cnc:" + line_complexity);
                                    line_complexity = 0;
                                    break;
                                } 
                            }

                        } 
                        else {
                            bw.write("," + line_complexity);
                        }
		                    

						//-----------------------------------------------------------------------------------------------------------------
						
						//-------------------------------------------------Calculating Ci--------------------------------------------------
						bw.write("," + arr[lineNo]);
						totalCi = totalCi + arr[lineNo];
						lineNo++;
						//-----------------------------------------------------------------------------------------------------------------
						
//						int lineCP = totalCs + totalCtc + total_complexity + totalCi;
//						bw.write("," + lineCP);
//						lineCP = 0;
						
						bw.write("\n");
					}
					double cp = totalCs + totalCtc + total_complexity + totalCi;
					bw.write(",,,,," + cp);
					reader.close();
					bw.close();
					
					
				} catch (IOException ex) {
					ex.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				File file = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Downloads\\result.csv");

				// first check if Desktop is supported by Platform or not
				if (!Desktop.isDesktopSupported()) {
					System.out.println("Desktop is not supported");
					return;
				}

				Desktop desktop = Desktop.getDesktop();
				if (file.exists())
					try {
						desktop.open(file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}

			}
		});
		exportReportButton.setBounds(176, 266, 261, 21);
		contentPane.add(exportReportButton);
		
		JLabel lblComplexityTool = new JLabel("COMPLEXITY TOOL");
		lblComplexityTool.setBounds(253, 102, 117, 45);
		contentPane.add(lblComplexityTool);
		
		JLabel lblUploadFile = new JLabel("Upload File:");
		lblUploadFile.setBounds(90, 181, 74, 13);
		contentPane.add(lblUploadFile);
	}
}
