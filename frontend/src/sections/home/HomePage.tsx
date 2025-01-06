import { FiBox } from 'react-icons/fi'
import { IoTelescopeOutline } from 'react-icons/io5'
import { useNavigate } from 'react-router-dom'
import {
  Box,
  Code,
  Container,
  Heading,
  HStack,
  Span,
  Tabs,
  Text,
  VStack,
} from '@chakra-ui/react'
import { Button } from '@/components/ui/button'
import Footer from '@/components/ui/footer'

const HomePage = () => {
  const navigate = useNavigate()

  const handleOnClick = () => {
    navigate('/pojo')
  }

  return (
    <>
      <Container py="16">
        <VStack gap="10">
          <VStack alignSelf="flex-start" maxW="3xl" gap="5" px="1.5">
            <Heading
              alignSelf="start"
              size={{ base: '4xl', md: '5xl' }}
              as="h1"
            >
              Pojo Generator <br /> herramienta para crear POJOs
            </Heading>
            <Text textStyle="2xl" color="gray.400" fontSize="xl">
              El principal objecto es facilitar el desarrollo al evitar la
              creación manual de clases Java para representar estructuras de
              datos complejas DTOs
            </Text>
          </VStack>
          <Box
            w="full"
            data-state="open"
            _open={{
              animationName: 'fade-in, scale-in',
              animationDuration: '300ms',
            }}
          >
            <Button
              onClick={() => handleOnClick()}
              variant="surface"
              colorPalette="teal"
              w={{ base: 'full', sm: '180px' }}
              size="xl"
            >
              Start Generator
            </Button>
          </Box>
        </VStack>
      </Container>
      <Box as="section" w="full" py="20">
        <Container w="full">
          <HStack
            alignItems="flex-start"
            gap="20"
            flexDir={{ base: 'column', md: 'row' }}
          >
            <VStack alignItems="flex-start" w="full" gap="4">
              <Box my="4">
                <Text color="teal.500" fontWeight="semibold">
                  Sistema de generación
                </Text>
              </Box>
              <Heading as="h2" size={{ base: '4xl', md: '5xl' }}>
                Construye tus POJO's con el poder de este sistema
              </Heading>
              <Text textStyle="2xl" color="gray.400" fontSize="lg">
                Dedique menos tiempo escribiendo Clases Java y más tiempo en la
                lógica del negocio.
              </Text>
              <Box as="ul" mt="4" display="flex" flexDir="column" gap="6">
                <Box as="li" display="flex" gap="2" alignItems="center">
                  <FiBox />
                  <Text>
                    Formato.{' '}
                    <Span color="gray.400">
                      Genera Clases Java con un formato de texto
                    </Span>
                  </Text>
                </Box>
                <Box as="li" display="flex" gap="2" alignItems="center">
                  <IoTelescopeOutline />
                  <Text>
                    Lombok.{' '}
                    <Span color="gray.400">
                      Clases Java con anotaciones Lombok
                    </Span>
                  </Text>
                </Box>
              </Box>
            </VStack>
            <VStack w="full">
              <Box rounded="lg" shadow="inset" w="full" h="440px" bg="black">
                <Tabs.Root defaultValue="simpleclass" variant="subtle" p="2">
                  <Tabs.List p="2">
                    <Tabs.Trigger value="simpleclass">
                      <FiBox />
                      Native POJO
                    </Tabs.Trigger>
                    <Tabs.Trigger value="lombokclass">
                      <FiBox />
                      Lombok POJO
                    </Tabs.Trigger>
                  </Tabs.List>
                  <Tabs.Content w="100%" value="simpleclass">
                    <Box h="100%" overflow="auto" position="relative">
                      <Box position="relative">
                        <Box as="pre" p="4" bg="black">
                          <Code
                            bg="transparent"
                            p="0"
                            display="inline"
                            fontFamily="Geist Mono, monospace"
                          >
                            <Box as="span" fontSize=".82rem">
                              <Box as="span" color="#FB5245">
                                package
                              </Box>
                              <Box as="span" color="#8EC07C">
                                {' '}
                                in
                              </Box>
                              <Box as="span" color="#EBDBB2">
                                ;
                              </Box>
                            </Box>
                            <br />
                            <br />
                            <Box as="span" fontSize=".82rem">
                              <Box as="span" color="#FB5245">
                                public
                              </Box>
                              <Box as="span" color="#FB5245">
                                {' class'}
                              </Box>
                              <Box as="span" color="#8EC07C">
                                {' '}
                                Person
                              </Box>
                              <Box as="span" color="#EBDBB2">
                                {' {'}
                              </Box>
                            </Box>
                            <br />
                            <Box as="span" fontSize=".82rem">
                              <Box as="span" color="#FB5245">
                                {'    private'}
                              </Box>
                              <Box as="span" color="#8EC07C">
                                {' '}
                                String
                              </Box>
                              <Box as="span" color="#EBDBB2">
                                {' '}
                                name
                              </Box>
                              <Box as="span" color="#EBDBB2">
                                ;
                              </Box>
                            </Box>
                            <br />
                            <br />
                            <Box as="span" fontSize=".82rem">
                              <Box as="span" color="#FB5245">
                                {'    public'}
                              </Box>
                              <Box as="span" color="#8EC07C">
                                {' '}
                                String
                              </Box>
                              <Box as="span" color="#FAC149">
                                {' '}
                                getName
                              </Box>
                              <Box as="span" color="#EBDBB2">
                                {'() {'}
                              </Box>
                            </Box>
                            <br />
                            <Box as="span" fontSize=".82rem">
                              <Box as="span" color="#FB5245">
                                {'        return'}
                              </Box>
                              <Box as="span" color="#FB5245">
                                {' '}
                                {'this'}
                              </Box>
                              <Box as="span" color="#EBDBB2">
                                {'.name;'}
                              </Box>
                            </Box>
                            <br />
                            <Box as="span" fontSize=".82rem">
                              <Box as="span" color="#EBDBB2">
                                {'    }'}
                              </Box>
                            </Box>
                            <br />
                            <br />
                            <Box as="span" fontSize=".82rem">
                              <Box as="span" color="#FB5245">
                                {'    public'}
                              </Box>
                              <Box as="span" color="#FB5245">
                                {' '}
                                void
                              </Box>
                              <Box as="span" color="#FAC149">
                                {' '}
                                setName
                              </Box>
                              <Box as="span" color="#EBDBB2">
                                {'('}
                              </Box>
                              <Box as="span" color="#8EC07C">
                                {'String'}
                              </Box>
                              <Box as="span" color="#EBDBB2">
                                {' '}
                                {'name'}
                              </Box>
                              <Box as="span" color="#EBDBB2">
                                {') {'}
                              </Box>
                            </Box>
                            <br />
                            <Box as="span" fontSize=".82rem">
                              <Box as="span" color="#FB5245">
                                {'        '}
                                {'this'}
                              </Box>
                              <Box as="span" color="#EBDBB2">
                                {'.name = name;'}
                              </Box>
                            </Box>
                            <br />
                            <Box as="span" fontSize=".82rem">
                              <Box as="span" color="#EBDBB2">
                                {'    }'}
                              </Box>
                            </Box>
                            <br />
                            <Box as="span" fontSize=".82rem">
                              <Box as="span" color="#EBDBB2">
                                {'}'}
                              </Box>
                            </Box>
                          </Code>
                        </Box>
                      </Box>
                    </Box>
                  </Tabs.Content>
                  <Tabs.Content value="lombokclass">
                    <Box h="100%" overflow="auto" position="relative">
                      <Box position="relative">
                        <Box as="pre" p="4" bg="black">
                          <Code
                            bg="transparent"
                            p="0"
                            display="inline"
                            fontFamily="Geist Mono, monospace"
                          >
                            <Box as="span" fontSize=".82rem">
                              <Box as="span" color="#FB5245">
                                package
                              </Box>
                              <Box as="span" color="#8EC07C">
                                {' '}
                                in
                              </Box>
                              <Box as="span" color="#EBDBB2">
                                ;
                              </Box>
                            </Box>
                            <br />
                            <br />
                            <Box as="span" fontSize=".82rem">
                              <Box as="span" color="#FB5245">
                                import
                              </Box>
                              <Box as="span" color="#8EC07C">
                                {' lombok.Getter'}
                              </Box>
                              <Box as="span" color="#EBDBB2">
                                ;
                              </Box>
                            </Box>
                            <br />
                            <Box as="span" fontSize=".82rem">
                              <Box as="span" color="#FB5245">
                                import
                              </Box>
                              <Box as="span" color="#8EC07C">
                                {' lombok.Setter'}
                              </Box>
                              <Box as="span" color="#EBDBB2">
                                ;
                              </Box>
                            </Box>
                            <br />
                            <br />
                            <Box as="span" fontSize=".82rem">
                              <Box as="span" color="#8EC07C">
                                {'@Getter'}
                              </Box>
                            </Box>
                            <br />
                            <Box as="span" fontSize=".82rem">
                              <Box as="span" color="#8EC07C">
                                {'@Setter'}
                              </Box>
                            </Box>
                            <br />
                            <Box as="span" fontSize=".82rem">
                              <Box as="span" color="#FB5245">
                                public
                              </Box>
                              <Box as="span" color="#FB5245">
                                {' class'}
                              </Box>
                              <Box as="span" color="#8EC07C">
                                {' '}
                                Person
                              </Box>
                              <Box as="span" color="#EBDBB2">
                                {' {'}
                              </Box>
                            </Box>
                            <br />
                            <Box as="span" fontSize=".82rem">
                              <Box as="span" color="#FB5245">
                                {'    private'}
                              </Box>
                              <Box as="span" color="#8EC07C">
                                {' '}
                                String
                              </Box>
                              <Box as="span" color="#EBDBB2">
                                {' id'}
                              </Box>
                              <Box as="span" color="#EBDBB2">
                                ;
                              </Box>
                            </Box>
                            <br />
                            <Box as="span" fontSize=".82rem">
                              <Box as="span" color="#FB5245">
                                {'    private'}
                              </Box>
                              <Box as="span" color="#8EC07C">
                                {' '}
                                String
                              </Box>
                              <Box as="span" color="#EBDBB2">
                                {' name'}
                              </Box>
                              <Box as="span" color="#EBDBB2">
                                ;
                              </Box>
                            </Box>
                            <br />
                            <Box as="span" fontSize=".82rem">
                              <Box as="span" color="#EBDBB2">
                                {'}'}
                              </Box>
                            </Box>
                          </Code>
                        </Box>
                      </Box>
                    </Box>
                  </Tabs.Content>
                </Tabs.Root>
              </Box>
            </VStack>
          </HStack>
        </Container>
      </Box>
      <Footer />
    </>
  )
}

export default HomePage
