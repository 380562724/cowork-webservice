import xml.etree.ElementTree as ET, glob, os

rows = []
tt = te = tf = ts = 0
for f in glob.glob("**/target/surefire-reports/TEST-*.xml", recursive=True):
    r = ET.parse(f).getroot()
    t, e, fa, sk = int(r.get("tests","0")), int(r.get("errors","0")), int(r.get("failures","0")), int(r.get("skipped","0"))
    tt += t; te += e; tf += fa; ts += sk
    sr = ((t-e-fa)/t*100) if t else 0
    rows.append('<tr class="a"><td>%s</td><td>%d</td><td>%d</td><td>%d</td><td>%d</td><td>%.1f%%</td></tr>' % (r.get("name",""), t, e, fa, sk, sr))
sr = ((tt-te-tf)/tt*100) if tt else 0
H = '<tr class="b"><td><b>Total</b></td><td><b>%d</b></td><td><b>%d</b></td><td><b>%d</b></td><td><b>%d</b></td><td><b>%.1f%%</b></td></tr>' % (tt, te, tf, ts, sr)
os.makedirs("target/site", exist_ok=True)
with open("target/site/surefire-report.html", "w") as h:
    h.write('<!DOCTYPE html><html><head><title>Surefire Report</title></head><body><div class="section"><h2><a name="Surefire_Report">Surefire Report</a></h2><table border="1" class="bodyTable"><tr class="a"><th>Package</th><th>Tests</th><th>Errors</th><th>Failures</th><th>Skipped</th><th>Rate</th></tr>' + H + "".join(rows) + '</table></div></body></html>')
print("Generated: %d tests" % tt)
