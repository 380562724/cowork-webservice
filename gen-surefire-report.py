import xml.etree.ElementTree as ET, glob, os

rows = []
tt = te = tf = ts = 0
for f in glob.glob("**/target/surefire-reports/TEST-*.xml", recursive=True):
    r = ET.parse(f).getroot()
    t = int(r.get("tests", "0"))
    e = int(r.get("errors", "0"))
    fa = int(r.get("failures", "0"))
    sk = int(r.get("skipped", "0"))
    tt += t; te += e; tf += fa; ts += sk
    pct = "%d" % (100 * (t - e - fa) // t) if t else "0"
    rows.append('<tr class="a"><td>%s</td><td>%d</td><td>%d</td><td>%d</td><td>%d</td><td>%s%%</td><td>0</td></tr>' % (r.get("name", ""), t, e, fa, sk, pct))

pct = "%d" % (100 * (tt - te - tf) // tt) if tt else "0"
summary_row = '<tr class="b"><td>%d</td><td>%d</td><td>%d</td><td>%d</td><td>%s%%</td><td>0</td></tr>' % (tt, te, tf, ts, pct)

os.makedirs("target/site", exist_ok=True)
with open("target/site/surefire-report.html", "w") as h:
    h.write('<body>'
            '<div class="section"><h2><a name="Summary">Summary</a></h2><br/>'
            '<table border="1" class="bodyTable">'
            '<tr class="a"><th>Tests</th><th>Errors</th><th>Failures</th><th>Skipped</th><th>Success Rate</th><th>Time</th></tr>'
            + summary_row +
            '</table></div>'
            '<div class="section"><h2><a name="Package_List">Package List</a></h2><br/>'
            '<table border="1" class="bodyTable">'
            '<tr class="a"><th>Package</th><th>Tests</th><th>Errors</th><th>Failures</th><th>Skipped</th><th>Success Rate</th><th>Time</th></tr>'
            + "".join(rows) +
            '</table></div>'
            '</body>')

print("Generated: %d tests, %d packages" % (tt, len(rows)))
