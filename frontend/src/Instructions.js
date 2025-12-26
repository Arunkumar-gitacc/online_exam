import { Container, Card, Button, Navbar, Row, Col } from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";
import { FaInfoCircle, FaClock, FaClipboardList, FaExclamationTriangle } from "react-icons/fa";
import logo from "./Assets/charani logo.webp"; 

function Instructions() {
  const { candidateId } = useParams();
  const navigate = useNavigate();
  const candidate = JSON.parse(localStorage.getItem("candidate") || "null");

  if (!candidate) return <h3 className="text-center mt-5">Unauthorized Access</h3>;

  return (
    <div style={{ backgroundColor: "#f4f7f9", minHeight: "100vh" }}>
      {/* Optimized Navbar */}
      <Navbar style={{ backgroundColor: "#3c20efff" }} variant="dark" className="shadow-sm mb-4">
        <Container>
          <Navbar.Brand className="d-flex align-items-center">
            <img
              src={logo}
              alt="Charani Logo"
              width="45"
              height="45"
              className="d-inline-block align-top me-2"
              style={{ borderRadius: "50%", backgroundColor: "white", padding: "2px" }}
            />
            <span className="fw-bold">CHARANI INFOTECH</span>
          </Navbar.Brand>
          <Navbar.Toggle />
          <Navbar.Collapse className="justify-content-end">
            <Navbar.Text className="text-white">
              <span className="opacity-75">Candidate Email:</span> <b>{candidate.email}</b>
            </Navbar.Text>
          </Navbar.Collapse>
        </Container>
      </Navbar>

      <Container className="pb-5">
        <Row className="justify-content-center">
          <Col md={10} lg={8}>
            <Card className="shadow-lg border-0 rounded-3">
              <Card.Header className="bg-white py-3 border-bottom">
                <h3 className="mb-0 text-primary d-flex align-items-center">
                  <FaInfoCircle className="me-2" /> Exam Instructions
                </h3>
              </Card.Header>
              <Card.Body className="p-4">
                
                {/* Exam Summary Tags */}
                <div className="d-flex flex-wrap gap-3 mb-4">
                  <div className="px-3 py-2 bg-light rounded-pill border">
                    <FaClock className="me-2 text-primary" /> <b>Duration:</b> 60 Mins
                  </div>
                  <div className="px-3 py-2 bg-light rounded-pill border">
                    <FaClipboardList className="me-2 text-primary" /> <b>Questions:</b> 60
                  </div>
                </div>

                <section className="mb-4">
                  <h5 className="fw-bold border-start border-4 border-primary ps-2 mb-3">General Rules</h5>
                  <ul className="lh-lg">
                    <li>Ensure you have a stable internet connection before starting.</li>
                    <li>The exam contains three sections: <b>Aptitude, Reasoning, and Communication.</b></li>
                    <li>Each question carries <b>1 mark</b>. There is <b>no negative marking</b>.</li>
                    <li>Once you click "Start Exam", the timer will begin and cannot be paused.</li>
                  </ul>
                </section>

                <section className="mb-4">
                  <h5 className="fw-bold border-start border-4 border-warning ps-2 mb-3">Restrictions</h5>
                  <ul className="lh-lg">
                    <li>Do not refresh the page or click the "Back" button during the exam.</li>
                    <li>Switching tabs or windows may lead to <b>automatic disqualification</b>.</li>
                    <li>Use of calculators, mobile phones, or reference materials is strictly prohibited.</li>
                  </ul>
                </section>

                <div className="bg-light p-3 rounded mb-4 d-flex align-items-start">
                  <FaExclamationTriangle className="text-warning me-3 mt-1" size={24} />
                  <p className="small mb-0 text-muted">
                    By clicking the button below, you acknowledge that you have read and understood the instructions. 
                    The exam will be automatically submitted once the time expires.
                  </p>
                </div>

                <div className="text-center">
                  <Button 
                    variant="primary" 
                    size="lg" 
                    className="px-5 py-3 fw-bold shadow"
                    onClick={() => navigate(`/exam/${candidateId}`)}
                  >
                    I AM READY TO START
                  </Button>
                </div>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>
    </div>
  );
}

export default Instructions;