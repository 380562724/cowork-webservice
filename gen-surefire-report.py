import xml.etree.ElementTree as ET, glob, os

rows = []
for f in glob.glob("**/target/surefire-reports/TEST-*.xml", recursive=True):
    r = ET.parse(f).getroot()
    t = int(r.get("tests", "0"))
    e = int(r.get("errors", "0"))
    fa = int(r.get("failures", "0"))
    sk = int(r.get("skipped", "0"))
    rows.append('<tr class="a"><td>%s</td><td>%d</td><td>%d</td><td>%d</td><td>%d</td></tr>' % (r.get("name", ""), t, e, fa, sk))

os.makedirs("target/site", exist_ok=True)
with open("target/site/surefire-report.html", "w") as h:
    h.write('<!DOCTYPE html><html><head><title>Surefire Report</title></head><body><div class="section"><h2><a name="Surefire_Report">Surefire Report</a></h2><table border="1" class="bodyTable"><tr class="a"><th>Package</th><th>Tests</th><th>Errors</th><th>Failures</th><th>Skipped</th></tr>' + "".join(rows) + '</table></div></body></html>')
print("Generated report with %d rows" % len(rows))
