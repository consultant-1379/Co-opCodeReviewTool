import pandas as pd
import numpy as np
from scipy import stats

#Group each subsection of the dataframe into a list
def group(df):
    df_list = []
    df_list.append(df.iloc[2:14])       #Programming and Scripting Languages
    df_list.append(df.iloc[16:22])      #Test automation
    df_list.append(df.iloc[24:31])      #Programming Platforms and Frameworks
    df_list.append(df.iloc[33:42])      #Source control, Build Tools and CI/CD tools
    df_list.append(df.iloc[45:47])      #Application Servers
    df_list.append(df.iloc[49:72])      #Execution environment, Application Platforms, Middleware and Architecture concepts
    df_list.append(df.iloc[75:81])      #Database & Data Store
    df_list.append(df.iloc[83:90])      #Graphicl User Interfaces and Web Presentation
    df_list.append(df.iloc[92:105])     #Software development processes and WOW
    df_list.append(df.iloc[108:115])    #Modeling Languages
    return df_list

#Get sub-heading from a reference index
def getHeading(index):
    return {
        0: 'Programming and Scripting Languages: ',
        1: 'Test automation: ',
        2: 'Programming Platforms and Frameworks: ',
        3: 'Source control, Build Tools and CI/CD tools: ',
        4: 'Application Servers: ',
        5: 'Execution environment, Application Platforms, Middleware and Architecture concepts: ',
        6: 'Database & Data Store: ',
        7: 'Graphicl User Interfaces and Web Presentation: ',
        8: 'Software development processes and WOW: ',
        9: 'Modeling Languages: '
    }.get(index, 'Invalid Index.') #else 

#Translate T,A,B,C,D to 1,2,3,4,5
def switch(x):
        return {
            'T': 1,
            'A': 2,
            'B': 3,
            'C': 4,
            'D': 5,
        }.get(x, 0) #else return 0 

#Apply switch to dataframe        
def translate(df):
    return df.apply(switch)

#Get the mode of each section
def calc_mode(df_list):
    modes = []
    for subsec in df_list:
        modes.append(stats.mode(subsec.to_numpy().flatten()))
    return modes

#Get means
def calc_mean(df_list):
    means = []
    for subsec in df_list:
        means.append(subsec.to_numpy().flatten().mean())

#imports excel sheets as dataframe objects
#Specify the file location, sheet name, columns of index/data
#specify full path, or just the name if it is in the same directory
#'r' to use special characters like '\'
df_anvil = pd.read_excel (r'Orchestration Competence Profile - SO - SW Competence Areas.xls',sheet_name=1, usecols = 'D:I')

df_autobots = pd.read_excel (r'Orchestration Competence Profile - SO - SW Competence Areas.xls',sheet_name=2, usecols = 'D:J') 

df_inferno = pd.read_excel (r'Orchestration Competence Profile - SO - SW Competence Areas.xls',sheet_name=3, usecols = 'D:M') 

df_architects = pd.read_excel (r'Orchestration Competence Profile - SO - SW Competence Areas.xls',sheet_name=4, usecols = 'D:F') 

df_core_team = pd.read_excel (r'Orchestration Competence Profile - SO - SW Competence Areas.xls',sheet_name=5, usecols = 'D:L') 

#Create list of each subsection
anvil_list = group(df_anvil)
autobots_list = group(df_autobots)
inferno_list = group(df_inferno)
architects_list = group(df_architects)
core_team_list = group(df_core_team)

#Apply translate function to each dataframe in the list
anvil_translated = []
autobots_translated = []
inferno_translated = []
architects_translated =[]
core_team_translated = []

for subsec in anvil_list:
    anvil_translated.append(subsec.apply(translate))

for subsec in autobots_list:
    autobots_translated.append(subsec.apply(translate))

for subsec in inferno_list:
    inferno_translated.append(subsec.apply(translate))

for subsec in architects_list:
    architects_translated.append(subsec.apply(translate))

for subsec in core_team_list:
    core_team_translated.append(subsec.apply(translate))

#Get modes for each subsection
anvil_modes      = calc_mode(anvil_translated)
autobots_modes   = calc_mode(autobots_translated)
inferno_modes    = calc_mode(inferno_translated)
architects_modes = calc_mode(architects_translated)
core_team_modes  = calc_mode(core_team_translated)

#Display modes
i = 0
print 'Anvil Modes: '
for averages in anvil_modes:
    print getHeading(i) + str(anvil_modes[i][0])
    i += 1

i = 0
print '\nAutobots Modes: '
for averages in autobots_modes:
    print getHeading(i) + str(autobots_modes[i][0])
    i += 1

i = 0
print '\nInferno Modes: '
for averages in inferno_modes:
    print getHeading(i) + str(inferno_modes[i][0])
    i += 1

i = 0
print '\nArchitects Modes: '
for averages in architects_modes:
    print getHeading(i) + str(architects_modes[i][0])
    i += 1

i = 0
print '\nCore Team Modes: '
for averages in core_team_modes:
    print getHeading(i) + str(core_team_modes[i][0])
    i += 1