import sys
import pandas as pd
import matplotlib.pyplot as plt
from reportlab.lib.styles import getSampleStyleSheet
from scipy.interpolate import make_interp_spline
from scipy import stats
import numpy as np
from pathlib import Path
import glob
import os
from reportlab.pdfgen import canvas
from reportlab.platypus import TableStyle
from reportlab.platypus import Table
from reportlab.lib.units import cm
from reportlab.lib.pagesizes import A4
from reportlab.platypus import Paragraph
from reportlab.lib.pagesizes import letter
from reportlab.platypus import SimpleDocTemplate, Image
from reportlab.lib import utils, colors
import PySimpleGUI as sg
import arabic_reshaper
from bidi.algorithm import get_display
from reportlab.pdfbase import pdfmetrics
from reportlab.pdfbase.ttfonts import TTFont
from reportlab.lib.styles import ParagraphStyle


all_sections_plots = []

width, height = A4

def coord(x, y, unit=1):
    x, y = x * unit, height -  y * unit
    return x, y


def creatSecPdfReport_ar(mean, std, corr, skew, maximum, minimum, subname, sectname, collname, deptname):
    pdfmetrics.registerFont(TTFont('Arabic', '/MEU/pt-bold-heading.ttf'))

    styles = getSampleStyleSheet()
    arabic_text_style = ParagraphStyle(
        'border',  # border on
        parent=styles['Normal'],  # Normal is a defaul style  in  getSampleStyleSheet
        fontName="Arabic",  # previously we named our custom font "Arabic"
        fontSize=13
    )

    arabic_text_style2 = ParagraphStyle(
        'border',  # border on
        parent=styles['Normal'],  # Normal is a defaul style  in  getSampleStyleSheet
        fontName="Arabic",  # previously we named our custom font "Arabic"
        fontSize=18,
        setFont='Arabic-bold'
    )

    arabic_text = [U"نموذج تقرير تحليل نتائج مقرر دراسي لعضو هيئة التدريس"  ,
                   "    الكلية  ",
                   "    القسم   ",
                   "    المقرر  ",
                   "    الشعبة  ",
                   "    نوع الإختبار    ",
                   "    درجة الإختبار   ",
                   "فصلي\نهائي",
                   "المؤشر",
                   "القيمة",
                   "المتوسط_الحسابي",
                   "الانحراف_المعياري",
                   "معامل_الإلتواء",
                   "معامل_الإرتباط",
                   "أقل قيمة",
                   "أكبر قيمة" ,
                   "المؤشرات الإحصائية لدرجات الطلاب في المقرر" ,
                   "تفسير المؤشرات الإحصائية مع الاسباب والتوصيات",
                   "الأسباب",
                   "التوصيات",
                   str(collname),
                   str(deptname),
                   u"""
                   إن معدل الطلاب اقل من المعدل الطبيعي وان انجراف الدرجات عن المعدل ليس كبيرا ومعامل الالتواءيميل الى اليسار اي اقل من المعدل الا ان معامل الارتباط يدل على ثبات مستوى طلاب المجموعة بين الفصلي والنهائي  
                   """]
    # reshape the text
    rehaped_text0 = arabic_reshaper.reshape(arabic_text[0])
    bidi_text0 = get_display(rehaped_text0)

    rehaped_text1 = arabic_reshaper.reshape(arabic_text[1])
    bidi_text1 = get_display(rehaped_text1)

    rehaped_text2 = arabic_reshaper.reshape(arabic_text[2])
    bidi_text2 = get_display(rehaped_text2)

    rehaped_text3 = arabic_reshaper.reshape(arabic_text[3])
    bidi_text3 = get_display(rehaped_text3)

    rehaped_text4 = arabic_reshaper.reshape(arabic_text[4])
    bidi_text4 = get_display(rehaped_text4)

    rehaped_text5 = arabic_reshaper.reshape(arabic_text[5])
    bidi_text5 = get_display(rehaped_text5)

    rehaped_text6 = arabic_reshaper.reshape(arabic_text[6])
    bidi_text6 = get_display(rehaped_text6)

    rehaped_text7 = arabic_reshaper.reshape(arabic_text[7])
    bidi_text7 = get_display(rehaped_text7)

    rehaped_text8 = arabic_reshaper.reshape(arabic_text[8])
    bidi_text8 = get_display(rehaped_text8)

    rehaped_text9 = arabic_reshaper.reshape(arabic_text[9])
    bidi_text9 = get_display(rehaped_text9)

    rehaped_text10 = arabic_reshaper.reshape(arabic_text[10])
    bidi_text10 = get_display(rehaped_text10)

    rehaped_text11 = arabic_reshaper.reshape(arabic_text[11])
    bidi_text11 = get_display(rehaped_text11)

    rehaped_text12 = arabic_reshaper.reshape(arabic_text[12])
    bidi_text12 = get_display(rehaped_text12)

    rehaped_text13 = arabic_reshaper.reshape(arabic_text[13])
    bidi_text13 = get_display(rehaped_text13)

    rehaped_text14 = arabic_reshaper.reshape(arabic_text[14])
    bidi_text14 = get_display(rehaped_text14)

    rehaped_text15 = arabic_reshaper.reshape(arabic_text[15])
    bidi_text15 = get_display(rehaped_text15)

    rehaped_text16 = arabic_reshaper.reshape(arabic_text[16])
    bidi_text16 = get_display(rehaped_text16)

    rehaped_text17 = arabic_reshaper.reshape(arabic_text[17])
    bidi_text17 = get_display(rehaped_text17)

    rehaped_text18 = arabic_reshaper.reshape(arabic_text[18])
    bidi_text18 = get_display(rehaped_text18)

    rehaped_text19 = arabic_reshaper.reshape(arabic_text[19])
    bidi_text19 = get_display(rehaped_text19)

    rehaped_text20 = arabic_reshaper.reshape(arabic_text[20])
    bidi_text20 = get_display(rehaped_text20)

    rehaped_text21 = arabic_reshaper.reshape(arabic_text[21])
    bidi_text21 = get_display(rehaped_text21)

    rehaped_text22 = arabic_reshaper.reshape(arabic_text[22])
    bidi_text22 = get_display(rehaped_text22)

    storys = []

    storys.append(Paragraph(bidi_text0, arabic_text_style2))
    storys.append(Paragraph(bidi_text1, arabic_text_style))
    storys.append(Paragraph(bidi_text2, arabic_text_style))
    storys.append(Paragraph(bidi_text3, arabic_text_style))
    storys.append(Paragraph(bidi_text4, arabic_text_style))
    storys.append(Paragraph(bidi_text5, arabic_text_style))
    storys.append(Paragraph(bidi_text6, arabic_text_style))
    storys.append(Paragraph(bidi_text7, arabic_text_style))
    storys.append(Paragraph(bidi_text8, arabic_text_style))
    storys.append(Paragraph(bidi_text9, arabic_text_style))
    storys.append(Paragraph(bidi_text10, arabic_text_style))
    storys.append(Paragraph(bidi_text11, arabic_text_style))
    storys.append(Paragraph(bidi_text12, arabic_text_style))
    storys.append(Paragraph(bidi_text13, arabic_text_style))
    storys.append(Paragraph(bidi_text14, arabic_text_style))
    storys.append(Paragraph(bidi_text15, arabic_text_style))
    storys.append(Paragraph(bidi_text16, arabic_text_style2))
    storys.append(Paragraph(bidi_text17, arabic_text_style2))
    storys.append(Paragraph(bidi_text18, arabic_text_style))
    storys.append(Paragraph(bidi_text19, arabic_text_style))
    storys.append(Paragraph(bidi_text20, arabic_text_style))
    storys.append(Paragraph(bidi_text21, arabic_text_style))
    storys.append(Paragraph(bidi_text22, arabic_text_style))

    fileName = '/MEU/Reports/'+sectname+'_'+subname+'.pdf'
    imagename = '/MEU/Figures/'+subname+'_'+sectname+'.png'

    img = utils.ImageReader(imagename)
    img_width, img_height = img.getSize()
    aspect = img_height / float(img_width)

    c = canvas.Canvas(fileName)
    c.setFontSize(20)

    c.saveState()
    c.drawImage(imagename, 0, 350, width=465, height=(465 * aspect))

    styles = getSampleStyleSheet()

    reasontext = storys[0]
    recommtext = Paragraph(createReasonAndRecommText(mean, std, skew, corr)[1], styles['Normal'])

    doc = SimpleDocTemplate(fileName, pagesize=letter)
    parts = [Image(imagename)]
    doc.build(parts)

    stats_para = [
        [storys[9]     , storys[8]],
        [str(mean)     , storys[10]],
        [str(std)      , storys[11]],
        [str(corr)     , storys[12]],
        [str(skew)     , storys[13]],
        [str(maximum)  , storys[14]],
        [str(minimum)   , storys[15]]
    ]

    data = [
        [storys[1], storys[2], storys[3], storys[4],storys[5],storys[6]],
        [storys[21], storys[20], '960', '1300',storys[7],'100']
    ]

    recommdata =[
        [storys[18]],
        [storys[22]],
        [storys[19]],
        [recommtext]

    ]

    ar_title1 = [[storys[0]]]
    ar_title2 = [[storys[16]]]
    ar_title3 = [[storys[17]]]

    title1_table = Table(ar_title1)
    title2_table = Table(ar_title2)
    title3_table = Table(ar_title3)
    table = Table(data)
    parameters_table = Table(stats_para)
    recomm_table = Table(recommdata)

    style = TableStyle([

        ('ALIGN', (0, 0), (-1, -1), 'CENTER'),

        ('FONTSIZE', (0, 0), (6, 6), 13),

        ('BOTTOMPADDING', (0, 0), (6, 6), 12),

        ('BOX', (0, 0), (6, 6), 1, colors.black),

        ('GRID', (0, 0), (6, 6), 1, colors.black)

    ])

    style2 = TableStyle([

        ('ALIGN', (0, 0), (-1, -1), 'RIGHT'),

        ('FONTSIZE', (0, 0), (6, 6), 20),

        ('BOTTOMPADDING', (0, 0), (6, 6), 12),
        ('BOX', (0, 0), (6, 6), 1, colors.black),

        ('GRID', (0, 0), (6, 6), 1, colors.black)

    ])

    style_no_grid = TableStyle([
        ('ALIGN', (0, 0), (-1, -1), 'CENTER'),
        ('FONTSIZE', (0, 0), (6, 6), 30)
    ])

    title1_table.setStyle(style_no_grid)
    title1_table.wrapOn(c, 500, 100)
    title1_table.drawOn(c, *coord(3.5, 1.0, cm))

    title2_table.setStyle(style_no_grid)
    title2_table.wrapOn(c,400,100)
    title2_table.drawOn(c, *coord(4.8, 5.4, cm))

    title3_table.setStyle(style_no_grid)
    title3_table.wrapOn(c, 500, 100)
    title3_table.drawOn(c, *coord(4.8, 18, cm))

    table.setStyle(style)
    table.wrapOn(c,500,70)
    table.drawOn(c, *coord(2.0, 3.8, cm))

    parameters_table.setStyle(style)
    parameters_table.wrapOn(c,100,100)
    parameters_table.drawOn(c, *coord(15.0, 13.2, cm))

    recomm_table.setStyle(style2)
    recomm_table.wrapOn(c,500,40)
    recomm_table.drawOn(c, *coord(1.5, 23.4, cm))

    c.save()


def creatSecPdfReport_eng(mean, std, corr, skew, maximum, minimum, subname, sectname):
    fileName = '/MEU/Reports/'+sectname+'_'+subname+'.pdf'
    imagename = '/MEU/Figures/'+subname+'_'+sectname+'.png'

    img = utils.ImageReader(imagename)
    img_width, img_height = img.getSize()
    aspect = img_height / float(img_width)

    c = canvas.Canvas(fileName)
    c.setFontSize(20)

    c.saveState()
    c.drawImage(imagename, 0, 350, width=465, height=(465 * aspect))

    styles = getSampleStyleSheet()
    coll_name_text = Paragraph("College of Computer Science", styles['Normal'])
    dep_name_text = Paragraph("Department of Computer Science", styles['Normal'])

    reasontext = Paragraph(createReasonAndRecommText(mean,std,skew,corr)[0], styles['Normal'])
    recommtext = Paragraph(createReasonAndRecommText(mean, std, skew, corr)[1], styles['Normal'])
    c.drawString(70,820,"A sample report for analyzing the results of a course")
    doc = SimpleDocTemplate(fileName, pagesize=letter)
    parts = [Image(imagename)]
    doc.build(parts)

    stats_para = [
        ['Parameter', 'Value'],
        ['mean', str(mean)],
        ['Std. Deviation', str(std)],
        ['Correlation', str(corr)],
        ['Skewness', str(skew)],
        ['Maximum', str(maximum)],
        ['Minimum', str(minimum)]
    ]

    data = [
        ['College', 'Department', 'Subject', 'Section','test type','test score'],
        [coll_name_text, dep_name_text, str(subname), str(sectname),'internal and final','100']
    ]

    recommdata =[
        ["Reasons :"],
        [reasontext],
        ["Recommendations :"],
        [recommtext]

    ]

    table = Table(data)
    table2 = Table(stats_para)
    table3 = Table(recommdata)

    style = TableStyle([

        ('ALIGN', (0, 0), (-1, -1), 'CENTER'),

        ('FONTSIZE', (0, 0), (6, 6), 16),

        ('BOTTOMPADDING', (0, 0), (6, 6), 12),
        ('BOX', (0, 0), (6, 6), 1, colors.black),

        ('GRID', (0, 0), (6, 6), 1, colors.black)

    ])

    style2 = TableStyle([

        ('ALIGN', (0, 0), (-1, -1), 'CENTER'),

        ('FONTSIZE', (0, 0), (6, 6), 20),

        ('BOTTOMPADDING', (0, 0), (6, 6), 12),
        ('BOX', (0, 0), (6, 6), 1, colors.black),

        ('GRID', (0, 0), (6, 6), 1, colors.black)

    ])

    table.setStyle(style)
    table.wrapOn(c,100,100)
    table.drawOn(c, *coord(2.0, 3.8, cm))

    table2.setStyle(style)
    table2.wrapOn(c,100,100)
    table2.drawOn(c, *coord(15.0, 13.2, cm))

    table3.setStyle(style2)
    table3.wrapOn(c,500,40)
    table3.drawOn(c, *coord(1.5, 24.3, cm))

    c.drawString(100,685,"Statistical parameters for students' scores")
    c.drawString(30,320,"Explanation of statistical indicators and the recommendations")
    c.save()


def creatSubjPdfReport(mean, std, corr, skew, maximum, minimum, subname, ttest,anova,total_students, two_secs_students):
    fileName = '/MEU/Reports/' + subname + '.pdf'
    imagename = '/MEU/Figures/' + subname +'.png'

    img = utils.ImageReader(imagename)
    img_width, img_height = img.getSize()
    aspect = img_height / float(img_width)

    c = canvas.Canvas(fileName)
    c.setFontSize(20)

    c.saveState()
    c.drawImage(imagename, 0, 350, width=465, height=(465 * aspect))

    styles = getSampleStyleSheet()
    coll_name_text = Paragraph("College of Computer Science", styles['Normal'])
    dep_name_text = Paragraph("Department Of computer Science", styles['Normal'])

    reasontext = Paragraph(createReasonAndRecommText(mean, std, skew, corr)[0], styles['Normal'])
    recommtext = Paragraph(createReasonAndRecommText(mean, std, skew, corr)[1], styles['Normal'])

    c.drawString(70, 820, "A sample report for analyzing the results of a course")
    doc = SimpleDocTemplate(fileName, pagesize=letter)
    parts = [Image(imagename)]
    doc.build(parts)

    stats_para = [
        ['Parameter', 'Value'],
        ['mean', str(mean)],
        ['Std. Deviation', str(std)],
        ['Correlation', str(corr)],
        ['Skewness', str(skew)],
        ['Maximum', str(maximum)],
        ['Minimum', str(minimum)]
    ]

    data = [
        ['College', 'Department', 'Subject', 'Section', 'test type', 'test score'],
        [coll_name_text, dep_name_text, str(subname), 'All', 'internal and final', '100']
    ]

    recommdata = [
        ["Reasons :"],
        [reasontext],
        ["Recommendations :"],
        [recommtext]

    ]

    comparesectiondata = [
        ['Parameter', 'Value', 'sig'],
        ['T Test(only 2 sections)', str(two_secs_students), str(ttest)],
        ['Oneway Anova(more than 2 sections)', str(total_students), str(anova)]
    ]

    table = Table(data)
    table2 = Table(stats_para)
    table3 = Table(recommdata)
    seccomparetable = Table(comparesectiondata)

    style = TableStyle([

        ('ALIGN', (0, 0), (-1, -1), 'CENTER'),

        ('FONTSIZE', (0, 0), (6, 6), 16),

        ('BOTTOMPADDING', (0, 0), (6, 6), 12),
        ('BOX', (0, 0), (6, 6), 1, colors.black),

        ('GRID', (0, 0), (6, 6), 1, colors.black)

    ])

    style2 = TableStyle([

        ('ALIGN', (0, 0), (-1, -1), 'CENTER'),

        ('FONTSIZE', (0, 0), (6, 6), 20),

        ('BOTTOMPADDING', (0, 0), (6, 6), 12),
        ('BOX', (0, 0), (6, 6), 1, colors.black),

        ('GRID', (0, 0), (6, 6), 1, colors.black)

    ])

    table.setStyle(style)
    table.wrapOn(c, 100, 100)
    table.drawOn(c, *coord(2.0, 3.8, cm))

    table2.setStyle(style)
    table2.wrapOn(c, 100, 100)
    table2.drawOn(c, *coord(15.0, 13.2, cm))

    seccomparetable.setStyle(style)
    seccomparetable.wrapOn(c, 100, 100)
    seccomparetable.drawOn(c, *coord(1.5, 20.7, cm))

    table3.setStyle(style2)
    table3.wrapOn(c, 535, 40)
    table3.drawOn(c, *coord(1.5, 28, cm))

    c.drawString(100, 690, "Statistical parameters for students' scores")
    c.drawString(30, 200, "Explanation of statistical indicators and the recommendations")
    c.drawString(30,340, "Statistical Parameters for compare of Sections students' scores")
    c.save()

def getStdsInfo(filepath):
    # read the excel file by colms from row 7 using header and extract its values only as integer
    stds_info1 = pd.read_excel(filepath, sheet_name=0,  usecols="A:C").dropna()
    columns_names = stds_info1.columns

    # renaming dataframe columns
    global stds_info
    stds_info = stds_info1.rename(columns ={columns_names[0]: 'mid', columns_names[1]: 'final', columns_names[2]: 'total'})

    global mean
    mean = stds_info['total'].mean().round(2)

    global corr
    corr = stds_info['mid'].corr(stds_info['final']).round(3)

    global std
    std = stds_info['total'].std().round(2)

    global skew
    skew = stds_info['total'].skew().round(2)

    global maximum
    maximum = stds_info['total'].max()

    global minimum
    minimum = stds_info['total'].min()

    global total_stds
    total_stds = len(stds_info['total'].values)

    stds_A_count = 0
    stds_B_count = 0
    stds_C_count = 0
    stds_D_count = 0
    stds_F_count = 0


    # distributing students based on there grades
    for x in range(total_stds):
        if 90 <= stds_info['total'].values[x] <= 100:
            stds_A_count = stds_A_count + 1

        elif 80 <= stds_info['total'].values[x] <= 89:
            stds_B_count = stds_B_count + 1

        elif 70 <= stds_info['total'].values[x] <= 79:
            stds_C_count = stds_C_count + 1

        elif 60 <= stds_info['total'].values[x] <= 69:
            stds_D_count = stds_D_count + 1

        else:
            stds_F_count = stds_F_count + 1

    global stds_dist
    stds_dist = [((stds_F_count / total_stds) * 100), ((stds_D_count / total_stds) * 100),
                 ((stds_C_count/total_stds)*100), ((stds_B_count/total_stds)*100),
                 ((stds_A_count/total_stds)*100)]
def calcDist(array):
    total = len(array['total'].values)

    stds_A_count = 0
    stds_B_count = 0
    stds_C_count = 0
    stds_D_count = 0
    stds_F_count = 0

    # distributing students based on there grades
    for x in range(len(array['total'])):
        if 90 <= array['total'].values[x] <= 100:
            stds_A_count = stds_A_count + 1

        elif 80 <= array['total'].values[x] <= 89:
            stds_B_count = stds_B_count + 1

        elif 70 <= array['total'].values[x] <= 79:
            stds_C_count = stds_C_count + 1

        elif 60 <= array['total'].values[x] <= 69:
            stds_D_count = stds_D_count + 1

        else:
            stds_F_count = stds_F_count + 1


    dist = [((stds_F_count / total) * 100), ((stds_D_count / total) * 100),
                 ((stds_C_count / total) * 100), ((stds_B_count / total) * 100),
                 ((stds_A_count / total) * 100)]

    return dist

def creat_plot(sect_name, cors_name,language):
    pdfmetrics.registerFont(TTFont('Arabic', '/MEU/pt-bold-heading.ttf'))

    arabic_text = ["رسم يوضح توزيع الطلاب على المعدل الطبيعي",
                   "توزيع الطلاب",
                   "التوزيع الطبيعي"]
    # reshape the text
    rehaped_text0 = arabic_reshaper.reshape(arabic_text[0])
    bidi_text0 = get_display(rehaped_text0)

    rehaped_text1 = arabic_reshaper.reshape(arabic_text[1])
    bidi_text1 = get_display(rehaped_text1)

    rehaped_text2 = arabic_reshaper.reshape(arabic_text[2])
    bidi_text2 = get_display(rehaped_text2)



    # normal distribution matrix
    y = [2,14,68,14,2]
    x = [1, 2, 3, 4, 5]
    # smoothing the curves
    model = make_interp_spline(x, y)
    xs = np.linspace(1, 5, 1000)
    ys = model(xs)

    model2 = make_interp_spline(x, stds_dist)
    new_xs = np.linspace(1, 5, 1000)
    new_ys = model2(new_xs)

    # creating plot
    if language == "ar":
        plt.figure()
        plt.plot(new_xs,new_ys, color='red', label=bidi_text1)
        plt.plot(xs, ys, label= bidi_text2)
        plt.title(bidi_text0)
        plt.legend(loc='upper right', frameon=False)
        plt.savefig('/MEU/Figures/'+cors_name+'_'+sect_name+'.png', dpi=80)
    else:
        plt.figure()
        plt.plot(new_xs, new_ys, color='red', label="Stds. Distribution")
        sec_plot = plt.plot(xs, ys, label= "Normal Distribution")
        all_sections_plots.append(sec_plot)
        plt.title("Students distribution over normal Distribution")
        plt.legend(loc='upper right', frameon=False)
        plt.savefig('/MEU/Figures/' + cors_name + '_' + sect_name + '.png', dpi=80)

    plt.close()

def creat_plot_sub( cors_name, all_dist):
    # normal distribution
    y = [2,14,68,14,2]
    x = [1, 2, 3, 4, 5]

    # smoothing the curves
    model = make_interp_spline(x, y)
    xs = np.linspace(1, 5, 500)
    ys = model(xs)

    model2 = make_interp_spline(x, all_dist)
    new_xs = np.linspace(1, 5, 500)
    new_ys = model2(new_xs)

    # creating plot
    plt.figure()
    plt.plot(new_xs,new_ys, color='red', label='Stds distribution')
    plt.plot(xs, ys, label="normal distribution")
    plt.title('Students distribution with normal distribution')
    plt.legend(loc='upper right', frameon=False)
    plt.savefig('/MEU/Figures/'+cors_name+'.png', dpi=60)

    plt.close()


def createReasonAndRecommText(mean, std, skew, corr):
    avr_text1 = "The average of students' scores is " + str(mean) + ", "
    mean_diff = mean - 68

    avr_above_normal = "showing that it is greatly above the normal average by " + str(mean_diff.round(3)) + " "
    avr_slightly_above_normal = "showing that it is greatly above the normal average by " + str(mean_diff.round(3)) + " "
    avr_is_normal = "showing that it is equal to the normal average "
    avr_slightly_under_normal = "showing that its slightly under the normal average by " + str(mean_diff.round(3)) + " "
    avr_under_normal = "showing that its greatly under the normal average by " + str(mean_diff.round(3)) + " "

    std_text1 = "and the deviation of grades  " + str(std) + ", "
    std_high = "is higher than usual value "
    std_close_val = "is not high and close to normal "

    skew_text1 = "and the skewness "
    skew_low = "tends to the left, less than the average. "
    skew_high = "tends to the right, higher than the average. "

    corr_text1 = "The correlation " + str(corr) + " "
    corr_high = "is high, it indicates a strong relation of students' scores between semester and final"
    corr_low = "is low, it indicates a weak relation of students' scores between semester and final"
    corr_norm = "is normal relation of students' scores between semester and final"

    reason_text_eng = avr_text1



    if mean == 68:
        reason_text_eng = reason_text_eng+avr_is_normal
        recomm_text_eng = "The results are at the normal level means students performance is normal, so we recommend giving students " \
                      "more exercises to raise up the performance level"
    elif mean > 68 and mean <= 70:
        reason_text_eng = reason_text_eng+avr_slightly_above_normal
        recomm_text_eng = "The results indicate good students performance."
    elif mean > 70:
        reason_text_eng = reason_text_eng+avr_above_normal
        recomm_text_eng = "The results indicate a great students performance."
    elif mean <68 and mean >=66:
        reason_text_eng = reason_text_eng+avr_slightly_under_normal
        recomm_text_eng = "The results indicate a slightly weak students performance, so we recommend giving students " \
                      "more exercises "
    else:
        reason_text_eng=reason_text_eng+avr_under_normal
        recomm_text_eng = "The results indicate weak students performance, so we recommend giving students " \
                      "more exercises "

    reason_text_eng = reason_text_eng +std_text1

    if std < (20-7) or 20:
        reason_text_eng=reason_text_eng+std_close_val
    else:
        reason_text_eng=reason_text_eng+std_high

    reason_text_eng=reason_text_eng+skew_text1

    if skew == 0:
        reason_text_eng = reason_text_eng+" normal skewness"
    elif skew < 0:
        reason_text_eng = reason_text_eng+skew_low
    else:
        reason_text_eng=reason_text_eng+skew_high

    reason_text_eng=reason_text_eng+corr_text1

    if corr == 0.5:
        reason_text_eng=reason_text_eng+corr_norm
    elif corr < 0.5:
        reason_text_eng=reason_text_eng+corr_low
    else:
        reason_text_eng=reason_text_eng+corr_high

    return [reason_text_eng, recomm_text_eng]

def main():

    # making a folder for the app
    Path("/MEU").mkdir(exist_ok=True)
    Path("/MEU/Reports").mkdir(exist_ok=True)
    Path("/MEU/Figures").mkdir(exist_ok=True)
    sg.theme('DarkAmber')
    layout1 =[[sg.Input(key='-IN-', enable_events=True, visible=False),sg.FolderBrowse('Choose files',key="-IN-")],
            [sg.Text('Please Choose a folder path that contain your subjects folders',key='textbox', size=(40,8))],
            [sg.Text("Select Report language "),
             sg.Radio('English', "RADIO1", default=True, key="-RD1-",),
             sg.Radio('Arabic', "RADIO1", default=False, key="-RD2-")],
            [sg.Submit(),sg.ProgressBar(1, orientation='h', size=(20, 20), key='progress_bar', visible=False)]]

    window = sg.Window('MEU', layout1, size=(500, 300))

    while True:             # Event Loop
        event, values = window.Read()
        if event in (None, 'Exit'):
            break
        elif event == "-IN-" :
            window.Element('textbox').Update("Folder path : "+str(values['-IN-']))
        elif event == "-RD1-":
            window.Element("English").Update(default=False)
        elif event == "-RD2-":
            window.Element("Arabic").Update(default=False)

        elif event == 'Submit' and values["-RD1-"] == True:
            window.Element('Submit').Update(visible=False)
            window.Element('progress_bar').Update(0,visible=True)

            try:
                # select a folder and get its path
                dirpath = values['-IN-']
                # list all folders in direpath
                fldlst = os.listdir(dirpath)


                filestotal = 0
                for root, dirs, files in os.walk(dirpath):
                    filestotal += len(files)

                files_done_count = 0
                progress_bar = window['progress_bar']

                # walking through folders and listing it to read it ang get its files one by one
                for x in range(len(fldlst)):
                    # getting subject names from folders listed in fldlst
                    cors_name = fldlst[x]
                    files = glob.glob(dirpath + "/" + fldlst[x] + '/*.*', recursive=True)
                    total_subj_stds = 0
                    sections = pd.DataFrame()
                    section_counter = 1
                    stds_count_in_2sections = 0
                    all_sections_plots.clear()
                    # reading files and getting information for sections
                    for file in range(len(files)):
                        window.Element('textbox').Update("reading :"+str(files[file]))
                        section_name_with_ext = os.path.basename(
                        files[file])  # section names from files' names with its extention
                        section_name = os.path.splitext(section_name_with_ext)[0]  # removing extention ".xlsx, .xls.. etc'
                        getStdsInfo(files[file])
                        creat_plot(section_name, cors_name,"eng")
                        creatSecPdfReport_eng(mean, std, corr, skew, maximum, minimum, cors_name, section_name)
                        total_subj_stds = total_subj_stds + total_stds
                        sections = sections.append(stds_info, ignore_index=True)
                        files_done_count= files_done_count+1

                        if section_counter <= 2:
                            twosections = sections.append(stds_info[0:2], ignore_index=True)
                            stds_count_in_2sections = stds_count_in_2sections + stds_info.shape[0]

                        progress_bar.update_bar(files_done_count, filestotal)
                        if files_done_count == filestotal:
                            window.Element('textbox').Update("done!, reports saved to C:/MEU/Reports")
                            window.Element('Submit').Update(visible=True)
                            window.Element('progress_bar').Update(0,visible=False)

                        section_counter= section_counter+1
                    allsecs_mean = sections['total'].mean().round(2)
                    allsecs_std = sections['total'].std().round(2)
                    allsecs_skew = sections['total'].skew().round(2)
                    allsecs_corr = sections['mid'].corr(sections['final']).round(3)
                    allsecs_max = sections['total'].max()
                    allsecs_min = sections['final'].min()
                    ttest_pvalue = stats.ttest_ind(twosections['mid'], twosections['final'])
                    oneway_pvalue = stats.f_oneway(sections['mid'], sections['final'])
                    print("students in 2 sections = "+str(stds_count_in_2sections))
                    creat_plot_sub(cors_name, calcDist(sections))
                    creatSubjPdfReport(allsecs_mean, allsecs_std, allsecs_corr, allsecs_skew, allsecs_max, allsecs_min,
                                   cors_name, ttest_pvalue[1], oneway_pvalue[1],total_subj_stds,stds_count_in_2sections)


            except:
                window.Element('textbox').Update('please choose a directory first')
                window.Element('Submit').Update(visible=True)
                window.Element('progress_bar').Update(0, visible=False)
                print(sys.exc_info())

        elif event == 'Submit' and values["-RD2-"] == True:
            window.Element('Submit').Update(visible=False)
            window.Element('progress_bar').Update(0,visible=True)

            try:
                # select a folder and get its path
                dirpath = values['-IN-']
                # list all folders in direpath
                fldlst = os.listdir(dirpath)


                filestotal = 0
                for root, dirs, files in os.walk(dirpath):
                    filestotal += len(files)

                files_done_count = 0
                progress_bar = window['progress_bar']

                # walking through folders and listing it to read it ang get its files one by one
                for x in range(len(fldlst)):
                    # getting subject names from folders listed in fldlst
                    cors_name = fldlst[x]
                    files = glob.glob(dirpath + "/" + fldlst[x] + '/*.*', recursive=True)
                    total_subj_stds = 0
                    sections = pd.DataFrame()
                    section_counter = 1
                    stds_count_in_2sections = 0
                    # reading files and getting information for sections
                    for file in range(len(files)):
                        window.Element('textbox').Update("reading :"+str(files[file]))
                        section_name_with_ext = os.path.basename(
                        files[file])  # section names from files' names with its extention
                        section_name = os.path.splitext(section_name_with_ext)[0]  # removing extention ".xlsx, .xls.. etc'
                        getStdsInfo(files[file])
                        creat_plot(section_name, cors_name,"ar")
                        creatSecPdfReport_ar(mean, std, corr, skew, maximum, minimum, cors_name, section_name,"كلية علوم الحاسب","قسم علوم الحاسب")
                        total_subj_stds = total_subj_stds + total_stds
                        sections = sections.append(stds_info, ignore_index=True)
                        files_done_count= files_done_count+1

                        if section_counter <= 2:
                            twosections = sections.append(stds_info[0:2], ignore_index=True)
                            stds_count_in_2sections = stds_count_in_2sections + stds_info.shape[0]

                        progress_bar.update_bar(files_done_count, filestotal)
                        if files_done_count == filestotal:
                            window.Element('textbox').Update("done!, reports saved to C:/MEU/Reports")
                            window.Element('Submit').Update(visible=True)
                            window.Element('progress_bar').Update(0,visible=False)

                        section_counter= section_counter+1

                    allsecs_mean = sections['total'].mean().round(2)
                    allsecs_std = sections['total'].std().round(2)
                    allsecs_skew = sections['total'].skew().round(2)
                    allsecs_corr = sections['mid'].corr(sections['final']).round(3)
                    allsecs_max = sections['total'].max()
                    allsecs_min = sections['final'].min()
                    ttest_pvalue = stats.ttest_ind(twosections['mid'], twosections['final'])
                    oneway_pvalue = stats.f_oneway(sections['mid'], sections['final'])
                    creat_plot_sub(cors_name,calcDist(sections))
                    creatSubjPdfReport(allsecs_mean, allsecs_std, allsecs_corr, allsecs_skew, allsecs_max, allsecs_min,
                                   cors_name, ttest_pvalue[1], oneway_pvalue[1],total_subj_stds,stds_count_in_2sections)


            except:
                window.Element('textbox').Update('please choose a directory first')
                window.Element('Submit').Update(visible=True)
                window.Element('progress_bar').Update(0, visible=False)


    window.Close()

main()


