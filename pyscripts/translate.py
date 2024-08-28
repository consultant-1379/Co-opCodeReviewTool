import pandas as pd
import numpy as np
import xlsxwriter

#Translate T,A,B,C,D to 1,2,3,4,5
def switch(x):
        return {
            'T': 1,
            'A': 2,
            'B': 3,
            'C': 4,
            'D': 5
        }.get(x, x) #else return the same value

#Apply switch to dataframe        
def translate(df):
    return df.apply(switch)

"""
#####################
#  Start of script  #
#####################
"""

#Specify path and name of the Excel file
path = r'Orchestration Competence Profile - SO - SW Competence Areas.xls'
xl = pd.ExcelFile (path)
sheets = xl.sheet_names

#Adds each sheet (consisting of 1 team) to a list of dataframes
teams = []
for sheet_index in range(len(xl.sheet_names)):
    team = pd.read_excel (xl, sheet_name = sheet_index)
    team = team.apply(translate)                          #Apply the translate function                      
    teams.append(team)


#Write dataframes to excel
writer = pd.ExcelWriter('Translated - ' + path, engine = 'xlsxwriter')  #Specifies path of file
i = 0
for team in teams:
    team.to_excel(writer, sheet_name = sheets[i], index = False)
    worksheet = writer.sheets[sheets[i]]
    for idx, col in enumerate(team):
        series = team[col]
        max_len = max((
            series.astype(str).map(len).max(),  #length of largest item
            len(str(series.name))  #length of column name/header
            )) + 1  #adding a little extra space
        worksheet.set_column(idx, idx, max_len)  #set column width
    i += 1
writer.save()